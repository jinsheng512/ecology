/**
 * @author 001965, 2018年12月28日 下午2:55:35
 */
package greedc.ws.client.dyd;

/**
 * 审批意见明细数据
 * @author 001965, 2018年12月28日 下午2:55:35
 */

public class DydOADanjuSpyjParams {

    private String fengkscx;//   风控审查项

    private String shencjg;// 审查结果

    private String fujzl;//   附件资料

    private String shenpyj;// 审批意见

    /**
     * @return the {@link #fengkscx}
     */
    public String getFengkscx() {
        return fengkscx;
    }

    /**
     * @param fengkscx the {@link #fengkscx} to set
     */
    public void setFengkscx(String fengkscx) {
        this.fengkscx = fengkscx;
    }

    /**
     * @return the {@link #shencjg}
     */
    public String getShencjg() {
        return shencjg;
    }

    /**
     * @param shencjg the {@link #shencjg} to set
     */
    public void setShencjg(String shencjg) {
        this.shencjg = shencjg;
    }

    /**
     * @return the {@link #fujzl}
     */
    public String getFujzl() {
        return fujzl;
    }

    /**
     * @param fujzl the {@link #fujzl} to set
     */
    public void setFujzl(String fujzl) {
        this.fujzl = fujzl;
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

    public DydOADanjuSpyjParams(String _fengkscx, String _shencjg, String _fujzl, String _shenpyj) {
        this.setFengkscx(_fengkscx);
        this.setShencjg(_shencjg);
        this.setFujzl(_fujzl);
        this.setShenpyj(_shenpyj);
    }
}
