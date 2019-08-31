package com.tpm.entity;

public class Fill_card {
	private int dxnum;          //多项填空题编号
	   private int minlength;      //最小长度
	   private int maxlength;      //最大长度
	   private int xianding;       //是否限定
	   private int bitian;         //是否必填
	   public int getDxnum() {
		   return dxnum;
	   }
	   public int getMinlength() {
		   return minlength;
	   }
	   public int getMaxlength() {
		   return maxlength;
	   }
	   public int getXianding() {
		   return xianding;
	   }
	   public int getBitian() {
		   return bitian;
	   }
	   public void setDxnum(int dxnum) {
		   this.dxnum=dxnum;
	   }
	   public void setMinlength(int minlength)
	   {
		   this.minlength=minlength;
	   }
	   public void setMaxlength(int maxlength) {
		   this.maxlength=maxlength;
	   }
	   public void setXianding(int xianding)
	   {
		   this.xianding=xianding;
	   }
	   public void setBitian(int bitian) {
		   this.bitian=bitian;
	   }
}
