package javgame.com.fwsj;

public class TestClass {
    @BindAddress("123")
    String address;
    @BindProt("123")
    private String port;

    private String constant = "http://www.baidu.com/";

    public void printInfo() {
        System.out.println("info " + address + ":" + port);
    }



    @BindGet(url = "sf", param = "zf")
    void getHttp(String parm) {
        System.err.println("url:" + parm);
    }

}
