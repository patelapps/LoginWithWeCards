

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
   compile 'com.github.User:Repo:Tag'
   }
```

## Step 3. How To Use... 

``` java
private loginWithWecards loginWithWecards;

loginWithWecards = new loginWithWecards(activity, new LoginHandler() {
  
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
![](https://www.we.cards/theme/images/more-feature-mockup.png)(https://github.com/tzutalin/dlib-android) + [FastRCNN](https://github.com/rbgirshick/caffe-fast-rcnn)
