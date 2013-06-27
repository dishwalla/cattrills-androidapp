package com.zeppelin.mygame;


public class Source {

	protected String user;
	protected String selectedUser;
	protected String inviter;
	protected Integer activityChangeCount;
	protected Integer iterations;
	protected boolean saveHistory;
	

	public boolean isSaveHistory() {
		return saveHistory;
	}

	public void setSaveHistory(boolean saveHistory) {
		this.saveHistory = saveHistory;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(String selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Integer getActivityChangeCount() {
		return activityChangeCount;
	}

	public void setActivityChangeCount(Integer activityChangeCount) {
		this.activityChangeCount = activityChangeCount;
	}

	public String getInviter() {
		return inviter;
	}

	public void setInviter(String inviter) {
		this.inviter = inviter;
	}

	public Integer getIterations() {
		return iterations;
	}

	public void setIterations(Integer iterations) {
		this.iterations = iterations;
	}

	
}
