import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        Pelanggan p1 = new Pelanggan("Mahfuzhah", "3812345678", 2000000, "1111"); // Silver
        Pelanggan p2 = new Pelanggan("Purba", "5612345678", 2000000, "2222"); // Gold
        Pelanggan p3 = new Pelanggan("Sistana", "7412345678", 2000000, "3333"); // Platinum

        System.out.println("Pilih pelanggan:");
        System.out.println("1. Mahfuzhah (Silver)");
        System.out.println("2. Purba (Gold)");
        System.out.println("3. Sistana (Platinum)");
        System.out.print("Pilih: ");
        int pilihUser = input.nextInt();

        Pelanggan pelanggan;

        switch (pilihUser) {
            case 1:
                pelanggan = p1;
                break;
            case 2:
                pelanggan = p2;
                break;
            case 3:
                pelanggan = p3;
                break;
            default:
                System.out.println("Pilihan tidak valid");
                return;
        }

        int pilihan;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Pembelian");
            System.out.println("2. Top Up");
            System.out.println("3. Cek Saldo");
            System.out.println("4. Keluar");
            System.out.print("Pilih: ");
            pilihan = input.nextInt();

            switch (pilihan) {
                case 1:
                    System.out.print("Jumlah belanja: ");
                    double belanja = input.nextDouble();
                    System.out.print("PIN: ");
                    String pin1 = input.next();

                    pelanggan.pembelian(belanja, pin1);
                    break;

                case 2:
                    System.out.print("Jumlah top up: ");
                    double topup = input.nextDouble();
                    System.out.print("PIN: ");
                    String pin2 = input.next();

                    pelanggan.topUp(topup, pin2);
                    break;

                case 3:
                    pelanggan.tampilSaldo();
                    break;

                case 4:
                    System.out.println("Terima kasih!");
                    break;

                default:
                    System.out.println("Pilihan tidak valid");
            }

        } while (pilihan != 4);

        input.close();
    }
}