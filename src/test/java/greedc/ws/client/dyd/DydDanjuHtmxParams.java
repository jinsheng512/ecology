/**
 * @author 001965, 2018年12月28日 下午2:55:35
 */
package greedc.ws.client.dyd;

/**
 * 合同明细数据
 * @author 001965, 2018年12月28日 下午2:55:35
 */
public class DydDanjuHtmxParams {

    private String hetlx;//    合同类型      类型   文本长度  32 1

    private String hetbh;//   合同编号      类型   文本长度  128 2

    private String hetje;//   合同金额      类型   小数位数     3

    private String lil;// 利率    类型   文本长度  12 4

    private String jiekqx;//  借款期限      类型   文本长度  12 5

    private String daysj;//   打印时间       类型   6

    private String hetdzb;//  合同电子版     类型   文本长度  256 7

    /**
     * @return the {@link #hetlx}
     */
    public String getHetlx() {
        return hetlx;
    }

    /**
     * @param hetlx the {@link #hetlx} to set
     */
    public void setHetlx(String hetlx) {
        this.hetlx = hetlx;
    }

    /**
     * @return the {@link #hetbh}
     */
    public String getHetbh() {
        return hetbh;
    }

    /**
     * @param hetbh the {@link #hetbh} to set
     */
    public void setHetbh(String hetbh) {
        this.hetbh = hetbh;
    }

    /**
     * @return the {@link #hetje}
     */
    public String getHetje() {
        return hetje;
    }

    /**
     * @param hetje the {@link #hetje} to set
     */
    public void setHetje(String hetje) {
        this.hetje = hetje;
    }

    /**
     * @return the {@link #lil}
     */
    public String getLil() {
        return lil;
    }

    /**
     * @param lil the {@link #lil} to set
     */
    public void setLil(String lil) {
        this.lil = lil;
    }

    /**
     * @return the {@link #jiekqx}
     */
    public String getJiekqx() {
        return jiekqx;
    }

    /**
     * @param jiekqx the {@link #jiekqx} to set
     */
    public void setJiekqx(String jiekqx) {
        this.jiekqx = jiekqx;
    }

    /**
     * @return the {@link #daysj}
     */
    public String getDaysj() {
        return daysj;
    }

    /**
     * @param daysj the {@link #daysj} to set
     */
    public void setDaysj(String daysj) {
        this.daysj = daysj;
    }

    /**
     * @return the {@link #hetdzb}
     */
    public String getHetdzb() {
        return hetdzb;
    }

    /**
     * @param hetdzb the {@link #hetdzb} to set
     */
    public void setHetdzb(String hetdzb) {
        this.hetdzb = hetdzb;
    }

    public DydDanjuHtmxParams(String _hetlx, String _hetbh, String _hetje, String _lil, String _jiekqx, String _daysj, String _hetdzb) {
        this.setHetlx(_hetlx);
        this.setHetbh(_hetbh);
        this.setHetje(_hetje);
        this.setLil(_lil);
        this.setJiekqx(_jiekqx);
        this.setDaysj(_daysj);
        this.setHetdzb(_hetdzb);

    }
}
