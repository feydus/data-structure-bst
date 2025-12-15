//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
class Dugum {
    int veri;
    Dugum sol;
    Dugum sag;

    Dugum(int veri) {
        this.veri = veri;
        this.sol = null;
        this.sag = null;
    }
}

class BST {
    Dugum root;

    BST() {
        root = null;
    }

    // 1) DIŞARIDAN ÇAĞIRDIĞIMIZ EKLEME METODU
    void ekle(int veri){ //kullanıcın çağırdığı metottur sadece eklenecek sayıyı alır ağaç içinde nasıl gezileceğini bilmez
        root = ekleRec(root, veri); //recursive metoda ağacın başlangıç noktası olan root ve eklenecek veri gönderiliyor
    } //neden root = yazıyoruz çünkü ağaç boş olabilir veya alt dallarda yeni düğüm eklenmiş olabilir
    //recursive metot geri dönüşte güncellenmiş düğümü verir bu dönüşü roota bağlamak zorundayız

    Dugum ekleRec(Dugum mevcut, int veri){ //ağacın içinde aşağı doğru dolaşır, doğru yeri bulur, yeni düğümü ekler, sonra geri yukarı çıkar
        if (mevcut == null){
            return new Dugum(veri);
        }
        //küçükse sola git
        if (veri < mevcut.veri) {
            mevcut.sol = ekleRec(mevcut.sol, veri);
        }
        //büyükse sağa git
        else if (veri > mevcut.veri){
            mevcut.sag = ekleRec(mevcut.sag, veri);
        }
        //eşitse hiçbir şey yapma
        //bağlantıları bozmadan geri dön
        return mevcut;
    }
    void inorderYazdir(){
        inorderRec(root);
        System.out.println();
    }
    void inorderRec(Dugum d){
        if (d == null) return;
        inorderRec(d.sol);
        System.out.print(d.veri + " ");
        inorderRec(d.sag);
    }
    boolean ara(int aranan){
        return araRec(root, aranan);
    }
    boolean araRec(Dugum mevcut, int aranan){
        if (mevcut == null){
            return false;
        }
        if (aranan == mevcut.veri){
            return true;
        }
        if (aranan < mevcut.veri){
            return araRec(mevcut.sol, aranan);
        }
        else {
            return araRec(mevcut.sag, aranan);
        }
    }
    void sil(int veri){
        root = silRec(root,veri);
    }
    Dugum silRec(Dugum mevcut, int veri){
        if (mevcut == null){
            return null;
        }
        if (veri<mevcut.veri){
            mevcut.sol = silRec(mevcut.sol, veri);
        } else if (veri>mevcut.veri) {
            mevcut.sag = silRec(mevcut.sag,veri);
        }
        //düğümü bulduk
        else {
            //durum 1 - yaprak düğüm
            if (mevcut.sol == null && mevcut.sag == null){
                return null;
            }
            //durum 2 - tek çocuk
            if (mevcut.sol == null){
                return mevcut.sag;
            }
            else if (mevcut.sag == null ){
                return mevcut.sol;
            }
            //durum 3 - iki çocuk
            Dugum minSag = minBul(mevcut.sag);
            mevcut.veri = minSag.veri;
            mevcut.sag = silRec(mevcut.sag , minSag.veri);
        }
        return mevcut;
    }
    Dugum minBul(Dugum dugum){
        while (dugum.sol != null){
            dugum = dugum.sol;
        }
        return dugum;
    }

}

public class Main {
    public static void main(String[] args) {
        BST agac = new BST();
        //ADIM ADIM EKLEME
        agac.ekle(10);
        agac.ekle(5);
        agac.ekle(15);
        agac.ekle(3);
        agac.ekle(7);
        System.out.print("Inorder (kucukten buyuge) : ");
        agac.inorderYazdir();
        System.out.println("7 var mı ? " + agac.ara(7));
        System.out.println("20 var mı ? " + agac.ara(20));
        agac.sil(3);
        agac.inorderYazdir();
    }
}
