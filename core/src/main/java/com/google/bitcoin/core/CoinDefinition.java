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
    public static boolean checkpointFileSupport = false;

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

    public static final int BLOCK_CURRENTVERSION = 112;
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
		
	if(nHeight < 3310000) { // Before LWMA-3
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
    }




}
