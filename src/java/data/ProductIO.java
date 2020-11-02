//
//package data;
//
//import business.Product;
//import java.io.BufferedReader;
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//
//public class ProductIO {
//    public static ArrayList<Product> getAllProduct(String path){
//        File file = new File(path);
//        ArrayList<Product> listProduct =  new ArrayList<>();
//        try {
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//           
//            String data = "";
//            while((data = br.readLine()) != null){
//                String[] result = data.split("\\|");
//                Product product = new Product(result[0],result[1],Double.parseDouble(result[2]));
//                listProduct.add(product);
//            }
//            br.close();
//            fr.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return listProduct;
//    }
//    
//    public static Product getByCode(String path, String code){
//        File file = new File(path);
//        ArrayList<Product> listProduct =  new ArrayList<>();
//        
//        try {
//            FileReader fr = new FileReader(file);
//            BufferedReader br = new BufferedReader(fr);
//           
//            String data = "";
//            while((data = br.readLine()) != null){
//                String[] result = data.split("\\|");
//                Product product = new Product(result[0],result[1],Double.parseDouble(result[2]));
//                listProduct.add(product);
//            }
//            
//            for(Product p : listProduct){
//                if(p.getCode().equals(code)){
//                    return p;
//                }
//            }
//            
//            br.close();
//            fr.close();
//        } catch (FileNotFoundException ex) {
//            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (IOException ex) {
//            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }
//    
//    public static void save(String path, ArrayList<Product> listProduct){
//        File file = new File(path);
//        
//        try {
//            FileWriter fw = new FileWriter(file);
//            BufferedWriter bw = new BufferedWriter(fw);
//            
//            for(Product p : listProduct){
//                bw.write(p.getCode()+"|"+p.getDescription()+"|"+String.valueOf(p.getPrice()));
//                bw.newLine();
//                bw.flush();
//            }
//            
//            bw.close();
//            fw.close();
//        } catch (IOException ex) {
//            Logger.getLogger(ProductIO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
//    
//    
//}
