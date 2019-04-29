/**
 * @author 001965, 2018年12月28日 下午2:55:35
 */
package greedc.ws.client.dyd;

/**
 * 业务流程审批意见明细数据
 * @author 001965, 2018年12月28日 下午2:55:35
 */

public class DydFlowSpmxParams {

    private String shephj;

    private String shenpr;

    private String shenpsj;

    private String shenpyj;

    private String shenpjg;

    private String beiz;

    /**
     * @return the {@link #shephj}
     */
    public String getShephj() {
        return shephj;
    }

    /**
     * @param shephj the {@link #shephj} to set
     */
    public void setShephj(String shephj) {
        this.shephj = shephj;
    }

    /**
     * @return the {@link #shenpr}
     */
    public String getShenpr() {
        return shenpr;
    }

    /**
     * @param shenpr the {@link #shenpr} to set
     */
    public void setShenpr(String shenpr) {
        this.shenpr = shenpr;
    }

    /**
     * @return the {@link #shenpsj}
     */
    public String getShenpsj() {
        return shenpsj;
    }

    /**
     * @param shenpsj the {@link #shenpsj} to set
     */
    public void setShenpsj(String shenpsj) {
        this.shenpsj = shenpsj;
    }

    /**
     * @return the {@link #shenpyj}
     */
    public String getShenpyj() {
        return shenpyj;
    }

    /**
     * @param shenpyj the {@link #shenpyj} to set
     */
    public void setShenpyj(String shenpyj) {
        this.shenpyj = shenpyj;
    }

    /**
     * @return the {@link #shenpjg}
     */
    public String getShenpjg() {
        return shenpjg;
    }

    /**
     * @param shenpjg the {@link #shenpjg} to set
     */
    public void setShenpjg(String shenpjg) {
        this.shenpjg = shenpjg;
    }

    /**
     * @return the {@link #beiz}
     */
    public String getBeiz() {
        return beiz;
    }

    /**
     * @param beiz the {@link #beiz} to set
     */
    public void setBeiz(String beiz) {
        this.beiz = beiz;
    }

    public DydFlowSpmxParams(String _shephj,String _shenpr, String _shenpsj, String _shenpyj,String _shenpjg, String _beiz) {
        this.setShephj(_shephj);
        this.setShenpr(_shenpr);
        this.setShenpsj(_shenpsj);
        this.setShenpjg(_shenpjg);
        this.setShenpyj(_shenpyj);
        this.setBeiz(_beiz);
    }
}
