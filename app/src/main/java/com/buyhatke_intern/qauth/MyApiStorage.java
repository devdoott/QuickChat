package com.buyhatke_intern.qauth;
import org.telegram.api.TLConfig;
import org.telegram.api.engine.storage.AbsApiState;
import org.telegram.mtproto.pq.PqAuth;
import org.telegram.mtproto.state.AbsMTProtoState;
import org.telegram.mtproto.state.ConnectionInfo;
import org.telegram.mtproto.pq.Authorizer;
import org.telegram.mtproto.state.KnownSalt;

/**
 * Created by Devdoot on 21-03-2016.
 */
public class MyApiStorage implements org.telegram.api.engine.storage.AbsApiState{
private int dc;
    private byte []key;
   private boolean auth;
    private long salt;
    ConnectionInfo[] connectionInfos=new ConnectionInfo[2];
    TLConfig t;
    MyApiStorage(){

        //connectionInfos[0]=new ConnectionInfo(1,0,"149.154.171.5",443);
    connectionInfos[0] =new ConnectionInfo( 1,0, "149.154.167.40", 443 );
        dc=1;
        connectionInfos[1] =new ConnectionInfo( 2,1, "149.154.167.50", 443 );

        //connectionInfos[3] =new ConnectionInfo( 4,3, "149.154.175.100", 443 );
       // connectionInfos[0] =new ConnectionInfo( 1,0, "149.154.167.91", 443 );
       // connectionInfos[0] =new ConnectionInfo( 1,0, "149.154.171.5", 443 );

        //connectionInfos[1]=new ConnectionInfo(2,2,"149.154.167.50",443);

        Authorizer a=new Authorizer();
        PqAuth pqAuth=a.doAuth(connectionInfos);

salt=pqAuth.getServerSalt();
       putAuthKey(1, pqAuth.getAuthKey());
    }

    @Override
    public int getPrimaryDc() {
        return dc;
    }

    @Override
    public boolean isAuthenticated(int i) {
        return auth;
    }

    @Override
    public void setAuthenticated(int i, boolean b) {
//if(dc==i)
        auth=b;
    }

    @Override
    public void updateSettings(TLConfig tlConfig) {
t=tlConfig;
    }


    @Override
    public void resetAuth() {
auth=false;
    }

    @Override
    public void putAuthKey(int i, byte[] bytes) {
        key=new byte[bytes.length];

key=bytes.clone();
    }

    @Override
    public ConnectionInfo[] getAvailableConnections(int i) {
        return connectionInfos;
    }

    @Override
    public AbsMTProtoState getMtProtoState(int i) {
        return new AbsMTProtoState() {
            @Override
            public byte[] getAuthKey() {
                return key;
            }

            @Override
            public ConnectionInfo[] getAvailableConnections() {
                return connectionInfos;
            }

            @Override
            public KnownSalt[] readKnownSalts() {
                KnownSalt[] ks =new KnownSalt[1];
                ks[0]=new KnownSalt(0,100000,salt);
                return ks;
            }

            @Override
            protected void writeKnownSalts(KnownSalt[] knownSalts) {
                knownSalts=new KnownSalt[1];
knownSalts[0]=new KnownSalt(0,100000,salt);

            }
        } ;
    }

    @Override
    public void reset() {
auth=false;
    }

    @Override
    public void setPrimaryDc(int i) {
dc=i;
    }

    @Override
    public byte[] getAuthKey(int i) {
    //    if (dc==i)
        return key.clone();
      //  return  null;
    }


}
