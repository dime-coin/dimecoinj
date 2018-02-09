package com.hashengineering.crypto;

import com.google.bitcoin.core.Sha256Hash;
import com.google.bitcoin.core.Sha512Hash;
import fr.cryptohash.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Hash Engineering on 2/18/14.
 */
public class Hash9 {

    private static final Logger log = LoggerFactory.getLogger(Hash9.class);
    private static boolean native_library_loaded = false;

    static {

        try {
            //System.loadLibrary("hash9");
            //native_library_loaded = true;
        }
        catch(UnsatisfiedLinkError e)
        {
            native_library_loaded = false;
        }
        catch(Exception e)
        {
            native_library_loaded = false;
        }
    }

    public static byte[] quarkDigest(byte[] input, int offset, int length)
    {
        byte [] buf = new byte[length];
        for(int i = 0; i < length; ++i)
        {
            buf[i] = input[offset + i];
        }
        return quarkDigest(buf);
    }

    public static byte[] quarkDigest(byte[] input) {
        //long start = System.currentTimeMillis();
        try {

            //return SCrypt.scrypt(input, input, 1024, 1, 1, 32);
            //return hash9(input);
            //byte [] java = hash9(input);
            //long javaTime = System.currentTimeMillis();
            //byte [] nativeResult = hash9_native(input);
            //long nativeTime = System.currentTimeMillis();
            //log.info("Hashing times: native {} ms vs java {} ms\n{}\n{}", nativeTime - javaTime, javaTime - start, new Sha256Hash(nativeResult), new Sha256Hash(java));
            //log.info("Hashing time:  native {} ms", nativeTime - start);
            //return nativeResult;
            return native_library_loaded ? hash9_native(input) : hash9(input);
        } catch (Exception e) {
            return null;
        }
        finally {
            //long time = System.currentTimeMillis()-start;
            //log.info("Quark Hash time: {} ms per block", time);
        }
    }

    static native byte [] hash9_native(byte [] input);


    static byte [] hash9(byte header[]/*, const T1 pbegin, const T1 pend*/)

    {

        Sha512Hash[] hash = new Sha512Hash[9];

        //sph_blake512_init(&ctx_blake);
        BLAKE512 blake512 = new BLAKE512();


        // ZBLAKE;
        hash[0] = new Sha512Hash(blake512.digest(header));
        BMW512 bmw = new BMW512();
        // ZBMW;
        hash[1] = new Sha512Hash(bmw.digest(hash[0].getBytes()));

        if((hash[1].getBytes()[0] & 8) != 0)
        {
            Groestl512 groestl = new Groestl512();
            // ZGROESTL;
            hash[2] = new Sha512Hash(groestl.digest(hash[1].getBytes()));
        }
        else
        {
            //sph_skein512_init(&ctx_skein);
            Skein512 skein = new Skein512();
            // ZSKEIN;
            //sph_skein512 (&ctx_skein, static_cast<const void*>(&hash[1]), 64);
            hash[2] = new Sha512Hash(skein.digest(hash[1].getBytes()));
            //sph_skein512_close(&ctx_skein, static_cast<void*>(&hash[2]));
            //log.info("hash[2] skein: " + hash[2].toString());
        }

        //sph_groestl512_init(&ctx_groestl);
        Groestl512 groestl = new Groestl512();
        // ZGROESTL;
        //sph_groestl512 (&ctx_groestl, static_cast<const void*>(&hash[2]), 64);
        hash[3] = new Sha512Hash(groestl.digest(hash[2].getBytes()));

         JH512 jh = new JH512();

        hash[4] = new Sha512Hash(jh.digest(hash[3].getBytes()));

        if((hash[4].getBytes()[0] & 8) != 0)
        {
            //  sph_blake512_init(&ctx_blake);
            BLAKE512 blake5 = new BLAKE512();
            // ZBLAKE;
            hash[5] = new Sha512Hash(blake5.digest(hash[4].getBytes()));
            // log.info("hash[5] blake: " + hash[5].toString());
        }
        else
        {
            //sph_bmw512_init(&ctx_bmw);
            BMW512 bmw5 = new BMW512();
            // ZBMW;
            hash[5] = new Sha512Hash(bmw5.digest(hash[4].getBytes()));
        }

        //sph_keccak512_init(&ctx_keccak);
        Keccak512 keccak = new Keccak512();
        // ZKECCAK;
        hash[6] = new Sha512Hash(keccak.digest(hash[5].getBytes()));

        Skein512 skein = new Skein512();
        // SKEIN;
        hash[7] = new Sha512Hash(skein.digest(hash[6].getBytes()));

        if((hash[7].getBytes()[0] & 8) != 0)
        {

            Keccak512 keccak7 = new Keccak512();
            // ZKECCAK;
            hash[8] = new Sha512Hash(keccak7.digest(hash[7].getBytes()));
        }
        else
        {
            //sph_jh512_init(&ctx_jh);
            JH512 jh7 = new JH512();
            // ZJH;
            hash[8] = new Sha512Hash(jh7.digest(hash[7].getBytes()));

        }


        return hash[8].trim256().getBytes();
    }
}
