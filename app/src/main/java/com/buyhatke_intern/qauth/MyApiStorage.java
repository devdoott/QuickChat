package com.buyhatke_intern.qauth;
import org.telegram.api.TLConfig;
import org.telegram.api.TLDcOption;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;
import org.telegram.mtproto.state.KnownSalt;
import org.telegram.mtproto.pq.*;
import  java.util.*;
public class MyApiStorage implements AbsApiState {
private byte[]key;
    private HashMap<Integer, ConnectionInfo[]> connections = new HashMap<Integer, ConnectionInfo[]>();
    private HashMap<Integer, byte[]> keys = new HashMap<Integer, byte[]>();
    private HashMap<Integer, Boolean> isAuth = new HashMap<Integer, Boolean>();
    private static TLConfig config;

    private int primaryDc = 2;

    public MyApiStorage() {
        connections.put(primaryDc, new ConnectionInfo[]{
                new ConnectionInfo(2, 0, "149.154.167.40", 443)

                 // Test
//new ConnectionInfo(1, 0, "173.240.5.1", 443) // Production

        });
        //key=null;
        //Authorizer a=new Authorizer();
        //PqAuth pqAuth=a.doAuth(getAvailableConnections(getPrimaryDc()));

        //salt=pqAuth.getServerSalt();
       // putAuthKey(getPrimaryDc(), pqAuth.getAuthKey());
// Authorizer a=new Authorizer();                    a.doAuth(connections.get(1));
    }

    @Override
    public byte[] getAuthKey(int dcId) {

        return keys.get(dcId);
    }

    @Override
    public ConnectionInfo[] getAvailableConnections(int dcId) {
        if (!connections.containsKey(dcId)) {
            return new ConnectionInfo[0];
        }
        return connections.get(dcId);
    }

    @Override
    public AbsMTProtoState getMtProtoState(final int dcId) {
        return new AbsMTProtoState() {
            private KnownSalt[] knownSalts = new KnownSalt[0];

            @Override
            public byte[] getAuthKey() {
                return MyApiStorage.this.getAuthKey(dcId);
            }

            @Override
            public ConnectionInfo[] getAvailableConnections() {
                return MyApiStorage.this.getAvailableConnections(dcId);
            }

            @Override
            public KnownSalt[] readKnownSalts() {
                return knownSalts;
            }

            @Override
            protected void writeKnownSalts(KnownSalt[] salts) {
                knownSalts = salts;
            }
        };
    }

    @Override
    public int getPrimaryDc() {
        return primaryDc;
    }

    @Override
    public boolean isAuthenticated(int dcId) {
        if (isAuth.containsKey(dcId)) {
            return isAuth.get(dcId);
        }
        return false;
    }

    @Override
    public void putAuthKey(int dcId, byte[] key) {
        keys.put(dcId, key);
    }

    @Override
    public void reset() {
        isAuth.clear();
        keys.clear();
    }

    @Override
    public void resetAuth() {
        isAuth.clear();
    }

    @Override
    public void setAuthenticated(int dcId, boolean auth) {
        isAuth.put(dcId, auth);
    }

    @Override
    public void setPrimaryDc(int dc) {
        primaryDc = dc;
    }

    @Override
    public void updateSettings(TLConfig confi) {
        connections.clear();
        HashMap<Integer, ArrayList<ConnectionInfo>> tConnections = new HashMap<Integer, ArrayList<ConnectionInfo>>();
        int id = 0;
        for (TLDcOption option : config.getDcOptions()) {
            if (!tConnections.containsKey(option.getId())) {
                tConnections.put(option.getId(), new ArrayList<ConnectionInfo>());
            }
            tConnections.get(option.getId()).add(new ConnectionInfo(id++, 0, option.getIpAddress(), option.getPort()));
        }

        for (Integer dc : tConnections.keySet()) {
            connections.put(dc, tConnections.get(dc).toArray(new ConnectionInfo[0]));
            setPrimaryDc(dc);
        }
    }
    public static  void setConfig(TLConfig tlConfig){
        config=tlConfig;
    }

}