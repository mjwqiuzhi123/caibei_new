package com.borrow.supermarket.condition;

import java.util.ArrayList;
import java.util.List;

public class LendManagerCondition
{
	  public static List<BaseConditionModel> getPlatformNature()
	  {
	    List platformNatureList = new ArrayList();
	    platformNatureList.add(new BaseConditionModel(1, "现金贷"));
	    platformNatureList.add(new BaseConditionModel(2, "信誉贷"));
	    platformNatureList.add(new BaseConditionModel(3, "房贷"));
	    platformNatureList.add(new BaseConditionModel(4, "车贷"));
	    return platformNatureList;
	  }

	  public static List<BaseConditionModel> getLendMoney()
	  {
	    List platformNatureList = new ArrayList();
	    platformNatureList.add(new BaseConditionModel(1, "500-1000"));
	    platformNatureList.add(new BaseConditionModel(2, "1000-2000"));
	    platformNatureList.add(new BaseConditionModel(3, "2000-3000"));
	    platformNatureList.add(new BaseConditionModel(4, "3000-5000"));
	    platformNatureList.add(new BaseConditionModel(5, "5000-8000"));
	    platformNatureList.add(new BaseConditionModel(6, "8000-10000"));
	    platformNatureList.add(new BaseConditionModel(7, "10000以上"));
	    return platformNatureList;
	  }

	  public static List<BaseConditionModel> getLendOnlineTime() {
	    List getLendOnlineTime = new ArrayList();
	    getLendOnlineTime.add(new BaseConditionModel(1, "3个月"));
	    getLendOnlineTime.add(new BaseConditionModel(2, "6个月 "));
	    getLendOnlineTime.add(new BaseConditionModel(3, "1年"));
	    getLendOnlineTime.add(new BaseConditionModel(4, "2年"));
	    getLendOnlineTime.add(new BaseConditionModel(5, "3年以上"));
	    return getLendOnlineTime;
	  }

	  public static List<BaseConditionModel> getCreditStanding()
	  {
	    List getCreditStanding = new ArrayList();
	    getCreditStanding.add(new BaseConditionModel(1, "不限"));
	    getCreditStanding.add(new BaseConditionModel(2, "无逾期"));
	    getCreditStanding.add(new BaseConditionModel(3, "1年逾期小于3次"));
	    getCreditStanding.add(new BaseConditionModel(4, "1年逾期超过3次"));
	    return getCreditStanding;
	  }

	  public static List<BaseConditionModel> getHasCredit()
	  {
	    List getHasCredit = new ArrayList();
	    getHasCredit.add(new BaseConditionModel(1, "有信用卡 "));
	    getHasCredit.add(new BaseConditionModel(2, "无信用卡"));
	    return getHasCredit;
	  }

	  public static List<BaseConditionModel> getLendPeriod()
	  {
	    List getLendPeriod = new ArrayList();
	    getLendPeriod.add(new BaseConditionModel(1, "7-14天"));
	    getLendPeriod.add(new BaseConditionModel(2, "14-21天"));
	    getLendPeriod.add(new BaseConditionModel(3, "21-28天"));
	    getLendPeriod.add(new BaseConditionModel(4, "30天"));
	    getLendPeriod.add(new BaseConditionModel(5, "30天以上 "));
	    return getLendPeriod;
	  }

	  public static List<BaseConditionModel> getHasActivity() {
	    List getHasActivity = new ArrayList();
	    getHasActivity.add(new BaseConditionModel(1, "活动进行中"));
	    getHasActivity.add(new BaseConditionModel(2, "无任何活动"));
	    return getHasActivity;
	  }

	  public static List<BaseConditionModel> getCashback()
	  {
	    List getCashback = new ArrayList();
	    getCashback.add(new BaseConditionModel(1, "未参与"));
	    getCashback.add(new BaseConditionModel(2, "已参与"));
	    getCashback.add(new BaseConditionModel(3, "审核通过"));
	    getCashback.add(new BaseConditionModel(4, "审核未通过"));
	    return getCashback;
	  }
}