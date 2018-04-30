package fr.ecole.eni.lokacar.dal;

import android.content.Context;

import fr.ecole.eni.lokacar.helper.VoitureHelper;

public class VoitureDAL {

    private VoitureHelper dbVoitureHelper;

    public VoitureDAL(Context context){
        dbVoitureHelper = new VoitureHelper(context);
    }



}
