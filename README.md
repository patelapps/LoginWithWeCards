

![](https://www.we.cards/theme/images/logo.png)  

# Login With WeCards

WeCards is a convenient and easy way to create, store, receive and share electronic business cards with your most updated contact information, allowing you to be contactable as and when you want.

## Step 1. Add the JitPack repository to your build file...
* Add it in your root build.gradle at the end of repositories:

``` java
allprojects {
  repositories   {
    maven { url 'https://jitpack.io' }
  }
}
```

## Step 2. Add the dependency...
* Add it in your module build.gradle dependencies:
``` java
dependencies{
     compile 'com.github.patelapps:LoginWithWeCards:1.3.63'
   }
```

## Step 3. How To Use... 

``` java

compileSdkVersion 28
minSdkVersion 16    
targetSdkVersion 28
    
private LoginWithWecards loginWithWecards;

loginWithWecards = new LoginWithWecards(activity, new LoginHandler() {
  
  @Override
  public void loginResult(String result) {
      //you will get login result here
   }
   
   @Override
   public void logoutResult(String result) {
      // you will get logout result here
   }
});


 @Override
    protected void onDestroy() {
        super.onDestroy();
        loginWithWecards.unRegisterCommunicator();
    }
```

## Application Screenshot
![](https://www.we.cards/theme/images/more-feature-mockup.png)

## Download 
* [Download Wecards From App Store](https://itunes.apple.com/app/wecards/id1154071458?ls=1&amp)  
* [Download Wecards From Play Store](https://play.google.com/store/apps/details?id=com.CCS.WeCards)

