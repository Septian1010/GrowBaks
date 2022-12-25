package com.kel_7.growbaks;

import java.util.ArrayList;

public class lapak_data {
    //memasukan seluruh data
    private static String[] lapakNama = {
            "Baso Aci Mang Ganteng",
            "Baso Aci Mang Kasep",
            "Baso Aci Mang Cakep",
            "Baso Aci Mang Asep"

    };
    private static String[] lapakDetail = {
            "Makanan",
            "Makanan",
            "Makanan",
            "Makanan"

    };
    private static int[] lapakGambar = {
            R.drawable.mang1,
            R.drawable.mang2,
            R.drawable.mang3,
            R.drawable.mang1

    };

    private static String[] lapakRate = {
            "4.5",
            "3.5",
            "2.5",
            "1.5"

    };

    static ArrayList<lapak> getListData(){
        ArrayList<lapak> list = new ArrayList<>();
        for(int position = 0; position < lapakNama.length; position++){
            lapak umkm = new lapak();
            umkm.setName(lapakNama[position]);
            umkm.setDetail(lapakDetail[position]);
            umkm.setPhotoThumbnail(lapakGambar[position]);
            umkm.setRate(lapakRate[position]);
            list.add(umkm);
        }
        return list;
    }

}

