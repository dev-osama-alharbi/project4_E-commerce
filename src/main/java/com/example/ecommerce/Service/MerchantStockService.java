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

    // تم تحويل الvoid الى boolean
    public boolean addMerchantStock(MerchantStock merchantStock){
        return merchantStocks.add(merchantStock);
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

    public boolean haveProduct(int merchantId,int productId) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantid() == merchantId) {
                if (merchantStocks.get(i).getProductid() == productId) {
                    if (merchantStocks.get(i).getStock() >= 1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void reduceProduct(int merchantId,int productId) {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getMerchantid() == merchantId) {
                if (merchantStocks.get(i).getProductid() == productId) {
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() - 1);
                }
            }
        }
    }
}
