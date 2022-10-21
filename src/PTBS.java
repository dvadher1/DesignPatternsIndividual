
public class PTBS {

	static Facade mainFacade = new Facade();
	
	public static void main(String[] args) {
		
		UserInfoItem userInfo = new UserInfoItem();
		mainFacade.createProductList();
		
		while(true) {
			boolean isTerminated;
			isTerminated = mainFacade.login(userInfo);
			if(isTerminated) break;
			
			mainFacade.createUser(userInfo);
			mainFacade.AttachProductToUser();
			if(userInfo.userType == UserInfoItem.USER_TYPE.Buyer) {
				mainFacade.remind();
			}
			boolean isLoggedOut = false;
			while(!isLoggedOut) {
				isLoggedOut = mainFacade.SelectProduct();
				if(isLoggedOut) {
					break;
				}
				isLoggedOut = mainFacade.productOperation();
			}
		}
	}

}
