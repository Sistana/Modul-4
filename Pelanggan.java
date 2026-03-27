import java.text.NumberFormat;
import java.util.Locale;

public class Pelanggan {
    private String nama;
    private String nomorPelanggan;
    private double saldo;
    private String pin;
    private int percobaanLogin;
    private boolean statusBlokir;

    public Pelanggan(String nama, String nomorPelanggan, double saldo, String pin) {
        this.nama = nama;
        this.nomorPelanggan = nomorPelanggan;
        this.saldo = saldo;
        this.pin = pin;
        this.percobaanLogin = 0;
        this.statusBlokir = false;
    
    }

    public boolean autentikasi(String nomorPelanggan, String pin) {
        if (statusBlokir) {
            return false;
        }

        if (!this.nomorPelanggan.equals(nomorPelanggan)) {
            return false;
        }

        if (this.pin.equals(pin)) {
            percobaanLogin = 0;
            return true;
        } else {
            percobaanLogin++;

            if (percobaanLogin >= 3) {
                statusBlokir = true;
            }
            return false;
        }
    }

    public double hitungCashback(double jumlahBelanja) {
        double cashback = 0;
        String kode = nomorPelanggan.substring(0, 2);
        
        if (kode.equals("38")) {
            if (jumlahBelanja > 1000000) {
                cashback = 0.05 * jumlahBelanja;
            }
        } else if (kode.equals("56")) {
            if (jumlahBelanja > 1000000) {
                cashback = 0.07 * jumlahBelanja;
            } else {
                cashback = 0.02 * jumlahBelanja;
            }
        } else if (kode.equals ("74")) {
            if (jumlahBelanja > 1000000) {
                cashback = 0.10 * jumlahBelanja;
            } else {
                cashback = 0.05 * jumlahBelanja;
            }
            
        }
        return cashback;
    }

    public void pembelian(double jumlahBelanja, String pin) {
        // cek blokir
        if (statusBlokir) {
            System.out.println("Akun diblokir");
            return;
        }
        // cek autentikasi
        if (!autentikasi(this.nomorPelanggan, pin)) {
            System.out.println("Autentikasi gagal");
            return;
        }
        // cek jumlah 
        if (jumlahBelanja <= 0) {
            System.out.println("Jumlah tidak valid");
            return;
        }
        // cek saldo
        if (saldo - jumlahBelanja < 10000) {
            System.out.println("Saldo tidak mencukupi");
            return;
        }
        // transaksi
        saldo -= jumlahBelanja;
        // cashback
        double cashback = hitungCashback(jumlahBelanja);
        saldo += cashback;
        System.out.println("Transaksi berhasil");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        System.out.println("Cashback: " + rupiah.format(cashback));
        System.out.println("Saldo sekarang: " + rupiah.format(saldo));
    }

    public void topUp(double jumlah, String pin) {
        // cek blokir
        if (statusBlokir) {
            System.out.println("Akun diblokir");
            return;
        }
        // cek autentikasi
        if (!autentikasi(this.nomorPelanggan, pin)) {
            System.out.println("Autentikasi gagal");
            return;
        }
        // transaksi
        if (jumlah <= 0) {
            System.out.println("Jumlah tidak valid");
            return;
        }
        saldo += jumlah;
        System.out.println("Top Up Berhasil");
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        System.out.println("Saldo sekarang: " + rupiah.format(saldo));
    }
    
    public void tampilSaldo() { 
        NumberFormat rupiah = NumberFormat.getCurrencyInstance(new Locale("id", "ID"));
        System.out.println("Saldo: " + rupiah.format(saldo)); 
    }
}