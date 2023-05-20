package com.example.ecommerce.Service;

import com.example.ecommerce.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks=new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStock(){
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(int id ,MerchantStock merchantStock){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId()==id){
                merchantStocks.set(i, merchantStock);
                return true;
            }

        }
        return false;
    }

    public boolean deleteMerchantStock(int id){
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId()==id){
                merchantStocks.remove(i);
                return true;
            }

        }
        return false;
    }

}
