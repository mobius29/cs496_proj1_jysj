package com.example.myapplication;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.myapplication.Contact.Contact;
import com.example.myapplication.Contact.ContactAdapter;
import com.example.myapplication.Gallery.GalleryAdapter;
import com.example.myapplication.Gallery.ImageFile;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;

public class Common {
    public static final String[] food_tags_id = {"tagKorean", "tagChinese", "tagJapanese", "tagItalian", "tagDessert", "tagETC"};
    public static final int[] food_tags_color = {R.drawable.checked_circle_korean, R.drawable.checked_circle_chinese, R.drawable.checked_circle_japanese, R.drawable.checked_circle_italian, R.drawable.checked_circle_dessert, R.drawable.checked_circle_etc};
    public static final String CONTACT_JSON_FILE_NAME = "contact.json";
    public static Stack<Integer> stack_page;
    public static ArrayList<Contact> allContacts, mContactList;
    public static ContactAdapter mAdapter;
    public static ArrayList<ImageFile> mGalleryList;
    public static GalleryAdapter galleryAdapter;

    // btn: 0: Home, 1: Contact, 2: Gallery, 3:IDK, 4: Contact Create, 5: Contact Detail, 6: Contact Update
    public static int currentTab = 0;
    public static int id_num = 0;

    public static void toPrev(Activity activity) {
        if(stack_page.empty()) System.exit(0);
        stack_page.pop();
        activity.finish();
    }

    public static void contactCopy(ArrayList<Contact> src, ArrayList<Contact> dest) {
        if(src != null && dest != null) {
            Collections.sort(src);

            dest.clear();
            dest.addAll(src);
        }
    }

    /*
    public static String getJsonString(Context context, String fileName) {
        String str = "";

        try {
            InputStream IS = context.getAssets().open(fileName);
            int f_size = IS.available();

            byte[] buffer = new byte[f_size];
            IS.read(buffer);
            IS.close();

            str = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return str;
    }

     */

    public static JSONObject contactsToJson() {
        try {
            JSONObject retObject = new JSONObject();
            JSONArray arr = new JSONArray();

            for(Contact contact: allContacts) {
                JSONObject content = new JSONObject();
                SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");

                content.put("id", Integer.toString(contact.getId()))
                        .put("name", contact.getName())
                        .put("phone", contact.getPhone())
                        .put("tags", contact.getTags())
                        .put("profileImage", contact.getProfileImage());

                if(contact.getLastMeet() == null) {
                    content.put("lastMeet", "");
                } else {
                    content.put("lastMeet", contact.getLastMeet());
                }

                arr.put(content);
            }
            retObject.put("contacts", arr);

            return retObject;
        } catch(JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void setTagsColor(TextView[] views, String checked) {
        String[] tagString = checked.replaceAll("[\\[\\]]", "").split(", ");
        for(int i = 0; i < 6; i++) {
            if(tagString[i].equals("true")){
                views[i].setBackgroundResource(food_tags_color[i]);
            }
        }
    }

    public static ArrayList<ImageFile> initGallery() {
        ArrayList<ImageFile> items = new ArrayList<>();
//        ArrayList<Integer> fids = new ArrayList<Integer>();

        items.add(new ImageFile("칵테일", R.drawable.image1, 4, "20180702", new ArrayList<Integer>()));
        items.add(new ImageFile("휘낭시에", R.drawable.image2, 4, "20190701", new ArrayList<Integer>()));
        items.add(new ImageFile("특등심까스", R.drawable.image3, 2, "20200627", new ArrayList<Integer>()));
        items.add(new ImageFile("냉모밀", R.drawable.image4, 2, "20210404", new ArrayList<Integer>()));
        items.add(new ImageFile("감자탕", R.drawable.image5, 0, "20211111", new ArrayList<Integer>()));
        items.add(new ImageFile("감바스", R.drawable.image6, 3, "20211201", new ArrayList<Integer>()));
        items.add(new ImageFile("고르곤졸라", R.drawable.image7, 3, "20211203", new ArrayList<Integer>()));
        items.add(new ImageFile("샐러드", R.drawable.image8, 3, "20220110", new ArrayList<Integer>()));
        items.add(new ImageFile("대한곱창", R.drawable.image9, 0, "20220212", new ArrayList<Integer>()));
        items.add(new ImageFile("김치말이국수", R.drawable.image10, 0, "20220330", new ArrayList<Integer>()));
        items.add(new ImageFile("라면", R.drawable.image11, 0, "20220524", new ArrayList<Integer>()));
        items.add(new ImageFile("홍대카페", R.drawable.image12, 4, "20220611", new ArrayList<Integer>()));
        items.add(new ImageFile("계란후라이", R.drawable.image14, 0, "20220629", new ArrayList<Integer>()));
        items.add(new ImageFile("고등어구이", R.drawable.image15, 0, "20220701", new ArrayList<Integer>()));
        items.add(new ImageFile("파전", R.drawable.image16, 0, "20220702", new ArrayList<Integer>()));

        return items;
    }

}
