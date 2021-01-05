package com.upt.cti.vlad.udrescu.booking.facebook_token;

public class HashKey {

    //TODO: when creating a facebook login this method should be in Main Activity and call it in onCreate() method

    /*@RequiresApi(api = Build.VERSION_CODES.P)
    private void printKeyHash(){
        try{
            PackageInfo packageInfo = getPackageManager().getPackageInfo(
                    getPackageName(),
                    PackageManager.GET_SIGNATURES
            );
            for(Signature signature : packageInfo.signatures){
                MessageDigest messageDigest = MessageDigest.getInstance("SHA");
                messageDigest.update(signature.toByteArray());
                Log.d("KEYHASH: ", Base64.encodeToString(messageDigest.digest(), Base64.DEFAULT));
            }
        }catch (PackageManager.NameNotFoundException e){
            e.printStackTrace();
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
    }*/

}
