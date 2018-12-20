package com.google.bitcoin.core;

import java.math.BigInteger;
import java.util.Date;
import java.util.Map;
import java.util.Vector;
import fr.cryptohash.BLAKE512;
import fr.cryptohash.BMW512;
import fr.cryptohash.Groestl512;
import fr.cryptohash.Skein512;
import fr.cryptohash.Keccak512;
import fr.cryptohash.JH512;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sun.management.resources.agent_ko;

/**
 * Created with IntelliJ IDEA.
 * User: HashEngineering
 * Date: 8/13/13
 * Time: 7:23 PM
 * Coin Definition for Dimecoin
 */
public class CoinDefinition {
    private static final Logger log = LoggerFactory.getLogger(CoinDefinition.class);

    public static final String coinName = "Dimecoin";
    public static final String coinTicker = "DIME";
    public static final String coinURIScheme = "dime";
    public static final String coinURIScheme2 = "dimecoin";
    public static final String coinInternalName = "dimecoin";
    public static final String PATTERN_PRIVATE_KEY_START = "6";

    public enum CoinPrecision {
        Coins,
        Millicoins,
    }
    public static final CoinPrecision coinPrecision = CoinPrecision.Coins;

    public static final String BLOCKEXPLORER_BASE_URL_PROD = "https://chainz.cryptoid.info/dime/";
    public static final String BLOCKEXPLORER_BASE_URL_TEST = "https://chainz.cryptoid.info/dime/";

    public static final String DONATION_ADDRESS = "74qwyi23gq15iJvGQShbSEQ5nvvRF3mWxp";

    enum CoinHash {
        SHA256,
        scrypt,
        quark
    };
    public static final CoinHash coinHash = CoinHash.quark;

    public static boolean checkpointFileSupport = true;
    //Original Values
    public static final int TARGET_TIMESPAN = (int)(65536);  // 18 hours minutes per difficulty cycle, on average.
    public static final int TARGET_SPACING = (int)(64);  // 64 seconds per block.
    public static final int INTERVAL = TARGET_TIMESPAN / TARGET_SPACING;  //1024 blocks

    public static final int getInterval(int height, boolean testNet) {
            return INTERVAL;
    }
    public static final int getTargetTimespan(int height, boolean testNet) {
            return TARGET_TIMESPAN;
    }
    public static int getMaxTimeSpan(int value, int height, boolean testNet)
    {
            return value * 110 / 100;
    }
    public static int getMinTimeSpan(int value, int height, boolean testNet)
    {
            return value / 2;
    }
    public static int spendableCoinbaseDepth = 50; //main.h: static const int COINBASE_MATURITY

    public static BigInteger COIN = BigInteger.valueOf(100000);
    public static BigInteger CENT = BigInteger.valueOf(1000);
    public static BigInteger mCOIN = BigInteger.valueOf(100);

    public static final int MAX_MONEY = 500000000;                 //main.h:  MAX_MONEY
    public static final String MAX_MONEY_STRING = "500000000";     //main.h:  MAX_MONEY

    public static final BigInteger DEFAULT_MIN_TX_FEE = BigInteger.valueOf(10);
    public static final BigInteger DUST_LIMIT = CoinDefinition.CENT; //main.h CTransaction::GetMinFee        0.01 coins

    public static final int PROTOCOL_VERSION = 70004;          //version.h PROTOCOL_VERSION
    public static final int MIN_PROTOCOL_VERSION = 209;        //version.h MIN_PROTO_VERSION

    public static final int BLOCK_CURRENTVERSION = 1;   //CBlock::CURRENT_VERSION
    public static final int MAX_BLOCK_SIZE = 1 * 1000 * 1000;


    public static final boolean supportsBloomFiltering = true; //Requires PROTOCOL_VERSION 70000 in the client

    public static final int Port    = 11931;       //protocol.h GetDefaultPort(testnet=false)
    public static final int TestPort = 21931;     //protocol.h GetDefaultPort(testnet=true)

    //
    //  Production
    //
    public static final int AddressHeader = 15;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS
    public static final int p2shHeader = 9;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS

    public static final int dumpedPrivateKeyHeader = 128;   //common to all coins
    public static final long PacketMagic = 0xfea503dd;      //0xfb, 0xc0, 0xb6, 0xdb

    //Genesis Block Information from main.cpp: LoadBlockIndex
    static public long genesisBlockDifficultyTarget = (0x1e0fffffL);         //main.cpp: LoadBlockIndex
    static public long genesisBlockTime = 1387807823L;                       //main.cpp: LoadBlockIndex
    static public long genesisBlockNonce = (16888732);                         //main.cpp: LoadBlockIndex
    static public String genesisHash = "00000d5a9113f87575c77eb5442845ff8a0014f6e79e2dd2317d88946ef910da"; //main.cpp: hashGenesisBlock
    static public String genesisMerkleRoot = "72596a6a36d42416b5486386c6e2b7e339782ef4eb49fb8a60ec7dc3475da545";
    static public int genesisBlockValue = 1;                                                              //main.cpp: LoadBlockIndex
    //taken from the raw data of the block explorer
    static public String genesisXInBytes = "04";   //"BIN COIN START"
    static public String genessiXOutBytes = "04678afdb0fe5548271967f1a67130b7105cd6a828e03909a67962e0ea1f61deb649f6bc3f4cef38c4f35504e51ec112de5c384df7ba0b8d578a4c702b6bf11d5f";

    /**
     * List of known peer ips taken from Coinexchange on June 15th, 2018.
     * Update 28/09/2018 : dimecoin seeders, nodes and mining pool node added instead
     */
    static public String[] dnsSeeds = new String[] {
        "seed1.dimecoinnetwork.com",
        "seed2.dimecoinnetwork.com",
        "node1.dimecoinnetwork.com",
        "node2.dimecoinnetwork.com",
        "dime-pool.dimecoinnetwork.com"
    };

    public static int minBroadcastConnections = 1;   //0 for default; we need more peers.
    //
    // TestNet - dimecoin - not tested
    //
    public static final boolean supportsTestNet = false;
    public static final int testnetAddressHeader = 119;             //base58.h CBitcoinAddress::PUBKEY_ADDRESS_TEST
    public static final int testnetp2shHeader = 199;             //base58.h CBitcoinAddress::SCRIPT_ADDRESS_TEST
    public static final long testnetPacketMagic = 0x011a39f7;      //0xfc, 0xc1, 0xb7, 0xdc
    public static final String testnetGenesisHash = "00000e5e37c42d6b67d0934399adfb0fa48b59138abb1a8842c88f4ca3d4ec96";
    static public long testnetGenesisBlockDifficultyTarget = (0x1e0fffffL);         //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockTime = 1373481000L;                       //main.cpp: LoadBlockIndex
    static public long testnetGenesisBlockNonce = (905523645);                         //main.cpp: LoadBlockIndex


    static final long _COIN = 100000;
    static final BigInteger nGenesisBlockRewardCoin = COIN;
    static final BigInteger nBlockRewardStartCoin = BigInteger.valueOf(2048 * _COIN);
    static final BigInteger nBlockRewardMinimumCoin = COIN;

    //main.cpp GetBlockValue(height, fee)
    static final BigInteger GetBlockValue(int nHeight)
    {

        if(nHeight == 0){
            return Utils.toNanoCoins(1, 0);
        }

		BigInteger nSubsidy = Utils.toNanoCoins(1024, 0);
		
		if(nHeight < 3310000){ // Before LWMA-3
			// Subsidy is cut in half every 512000 blocks
			nSubsidy = nSubsidy.shiftRight(nHeight / 512000);

			long modNumber = nHeight % 1024;

			if(modNumber == 0){
				modNumber = 1024; //every 1024 have a big bonus
			}

			nSubsidy = nSubsidy.multiply(BigInteger.valueOf(modNumber));

			//premined 8% for dev, support, bounty, and giveaway etc
			if(nHeight > 9 && nHeight < 128){
				nSubsidy = Utils.toNanoCoins(350000000, 0);
			}
		else {
			nSubsidy = Utils.toNanoCoins(8192, 0);
		}

        return nSubsidy;
    }


    public static int subsidyDecreaseBlockCount = 512000;     //main.cpp GetBlockValue(height, fee)

    public static BigInteger proofOfWorkLimit = Utils.decodeCompactBits(0x1e0fffffL);  //main.cpp bnProofOfWorkLimit (~uint256(0) >> 20); // dimecoin: starting difficulty is 1 / 2^12

    //from main.h: CAlert::CheckSignature
    public static final String SATOSHI_KEY = "04e7fc3ea64e8fc816371c8ecb26852f9b864e584da18464999accfebb627e3196cc4266d778f2ba3394752a76027b7e1d9e981b1098b6441f26034314bd9a8a26";
    public static final String TESTNET_SATOSHI_KEY = "04c5aca36066b187e3cd2ff178309c652a4196cc230e8018ce2afed658bd0c073cba4c93b1c119f51ab65a5af8851c3bda386c35bcf45b77a34050a0b6e042f7da";

    /** The string returned by getId() for the main, production network where people trade things. */
    public static final String ID_MAINNET = "org.dimecoin.production";
    /** The string returned by getId() for the testnet. */
    public static final String ID_TESTNET = "org.dimecoin.test";
    /** Unit test network. */
    public static final String ID_UNITTESTNET = "com.google.dimecoin.unittest";

    //checkpoints.cpp Checkpoints::mapCheckpoints
    public static void initCheckpoints(Map<Integer, Sha256Hash> checkpoints)
    {
        checkpoints.put( 0,         new Sha256Hash("00000d5a9113f87575c77eb5442845ff8a0014f6e79e2dd2317d88946ef910da"));
        checkpoints.put( 20000,     new Sha256Hash("0000008e1581ca6fc2a796401be25dd68bd444a286a322f1f7354b2f102e2e26"));
        checkpoints.put( 30000,     new Sha256Hash("00000034a4be19182d0e8e56bad69541be2e6fa3274043e6c0fb90ad4a436820"));
        checkpoints.put( 40000,     new Sha256Hash("0000004227fb1fc8dfd51919cdc080ede60c3c8590b38e6c88b3d733d805b779"));
        checkpoints.put( 50000,     new Sha256Hash("0000001f6750308a0683f327488c0393e2b255ad0aa61d88716cd10bd8d44b5f"));
        checkpoints.put( 60000,     new Sha256Hash("00000007b938fb5231039fb2f5330cce860e3db7a6542d411bfa796e0c88b20d"));
        checkpoints.put( 70000,     new Sha256Hash("00000001a9f5cb9dd8a033b0b112bd946795bfd9266bff087eebce699e028696"));
        checkpoints.put( 80000,     new Sha256Hash("00000000f87ac340b2ac8f3a33659b27857a1af7a4a2c69b6c6ec85af54b155b"));
        checkpoints.put( 90000,     new Sha256Hash("0000000054e78ffd1a9bed31df6833bd5d86b79f3b18d45dc8c6e8455a5ac6c3"));
        checkpoints.put( 100000,    new Sha256Hash("00000000539760ef8dd9d933743b6281e8337359ecfa35917f924e52156c7566"));
        checkpoints.put( 109346,    new Sha256Hash("0000000003a8062398ad2479884ee028ca0b7cc2daa143b3173afa7068976b74"));
        checkpoints.put( 250000,    new Sha256Hash("000000001ecc6fd9f2a2e50722762de18bc96599e6d00e3ddc9e2d97d4b177ff"));
        checkpoints.put( 350000,    new Sha256Hash("000000001929a0b35aa3199a45d6f5a4a936b3347ae5cea2cea2b8b5ffdab731"));
        checkpoints.put( 500000,    new Sha256Hash("00000000129e32df9b68e9478fb99ad094ca3311e26b853a07d864c557ae3696"));
        checkpoints.put( 600000,    new Sha256Hash("000000000c12b6426aafd24960e86704c7a2eb4b73f96211f00070d939f3bbba"));
        checkpoints.put( 700000,    new Sha256Hash("000000002954073d358a9c13de05711b7be77d669b02c40435931e801cd1c4ed"));
        checkpoints.put( 800000,    new Sha256Hash("00000000063b01cae9db85adab32585e02f8c31ac9c26c22a5372408db32ef72"));
        checkpoints.put( 900000,    new Sha256Hash("0000000018b771af50cb42842fdf16276458b27f20c8e95595e05bacd5c377ab"));
        checkpoints.put(1000000,    new Sha256Hash("000000001e2ef1ef0f9f587150e598faa2e74ff95c0bcdd2026d2688b2bbf13a"));
        checkpoints.put(1100000,    new Sha256Hash("00000000355853adaf8f538c6395b6ab08eb37ada54996b3ac3ff56f1e1cd372"));
        checkpoints.put(1200000,    new Sha256Hash("0000000003edb0a84251f028361def13930b5ce87adc34680100d3a0f8563418"));
        checkpoints.put(1300000,    new Sha256Hash("000000015c75a460f24f920db11fd4f6a98fbe5242253bc7c98e3f2900d05586"));
        checkpoints.put(1400000,    new Sha256Hash("000000002c8fcd56cf4b0a53aa0d7dbf0050ad73fcc218d4015fb70070863d7e"));
        checkpoints.put(1500000,    new Sha256Hash("000000007f0008d7b697589583d58cc0d83a62f7364859711576fe50bca70f06"));
        checkpoints.put(1550000,    new Sha256Hash("000000004952592fc7376464d63412f01d2877587a19a08432edd788b005723e"));
        checkpoints.put(1700000,    new Sha256Hash("00000003b6cd157bfe643cd3c624ec32096709d2bea77191def23ab62b61b73d"));
        checkpoints.put(1800000,    new Sha256Hash("00000000002f3679c71961e3d989244a2f4e4e9cb75ccb7ff7ac3c500d25ee19"));
        checkpoints.put(1900000,    new Sha256Hash("0000000001acf206f3c7e4d181f550281cae2735ef1a427f089f45f3651c5717"));
        checkpoints.put(2000000,    new Sha256Hash("000000000a212f4cfab5d16c26868c37067a4642691b77230bb68a60a10c97e1"));
        checkpoints.put(2100000,    new Sha256Hash("000000002be14a1a595300b753bb65ac332bfa9715db3535c09d02b0122d8c82"));
        checkpoints.put(2200000,    new Sha256Hash("0000000028908252525e08a5039a6a2f7f4dc5dae8715b30e86af9286134411f"));
        checkpoints.put(2300000,    new Sha256Hash("0000000000dac114de7426040d12269c3b48724a64a186d1fb82b0be5dd3f07b"));
        checkpoints.put(2400000,    new Sha256Hash("0000000000cb3e9c9c2082e80465ea6780320fb3481d67964bcd6965a2077380"));
        checkpoints.put(2500000,    new Sha256Hash("0000000000010f978067c92689a39ef8c6f17849a6e3c6c86ac8685e8f7f84af"));
        checkpoints.put(2600000,    new Sha256Hash("0000000000154d18cff372412d641e4f4a17797e1eb5a1ff714910b31dad1632"));
        //new checkpoints added in version 1.9.0.0
        checkpoints.put(2700000,    new Sha256Hash("000000000005fb1c672edbd26aad6e86db15dc6b7f2ad9bf0bdefdd7a2fe74c5"));
        checkpoints.put(2800000,    new Sha256Hash("000000000001ee0e0a348d177511ed2d79b4ec98359242c0b386e1931d42489a"));
        checkpoints.put(2900000,    new Sha256Hash("000000000002c4539330ab986e1fa4f2fe720559d8858c381859f3b896cac7a5"));
        //new checkpoints added in version 1.9.0.2
        checkpoints.put(3000000,    new Sha256Hash("000000000000c7552284192bd1e3e7b1b7648ec4868cef082f2aa4945f7bad36"));
        checkpoints.put(3100000,    new Sha256Hash("00000000000e46ee7f527b6ba4b557ef49a9c17ae4a852a7e0fe3023048288f4"));
        //new checkpoint added in version 1.9.1.0
        checkpoints.put(3200000,    new Sha256Hash("0000000000015dfaf2bf3f4c05192f61baa66bd682df1359bf77bfa07c2c9e0c"));
    }




}
