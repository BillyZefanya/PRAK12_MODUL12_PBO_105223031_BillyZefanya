import java.util.InputMismatchException;
import java.util.Scanner;
/*
 * Main hanya bertugas menjalankan program
 * dan menerima input pengguna.
 *
 * SRP
 * Logika reservasi tidak ditulis di Main,
 * tetapi dipindahkan ke ReservasiService.
 */
public class Main {

    public static void main(String[] args) {

        Scanner masukanPengguna =
                new Scanner(System.in);

        RepositoryKereta repositoryKereta =
                new RepositoryKereta();

        ValidatorPenumpang validatorPenumpang =
                new ValidatorPenumpang();

        ReservasiService reservasiService =
                new ReservasiService(
                        repositoryKereta,
                        validatorPenumpang);

        int pilihanMenu = 0;

        do {

            try {

                System.out.println("\n========================");
                System.out.println("     JAVA EXPRESS");
                System.out.println("========================");
                System.out.println("1. Lihat Jadwal");
                System.out.println("2. Pesan Tiket");
                System.out.println("3. Keluar");

                System.out.print("Pilih Menu : ");
                pilihanMenu =
                        masukanPengguna.nextInt();

                masukanPengguna.nextLine();

                switch (pilihanMenu) {

                    case 1:

                        reservasiService
                                .tampilkanJadwal();

                        break;

                    case 2:

                        System.out.print(
                                "Kode Kereta : ");

                        String kodeKereta =
                                masukanPengguna.nextLine();

                        System.out.print(
                                "NIK : ");

                        String nikPenumpang =
                                masukanPengguna.nextLine();

                        System.out.print(
                                "Nama Penumpang : ");

                        String namaPenumpang =
                                masukanPengguna.nextLine();

                        System.out.print(
                                "Jumlah Tiket : ");

                        int jumlahTiket =
                                masukanPengguna.nextInt();

                        masukanPengguna.nextLine();

                        reservasiService.pesanTiket(
                                kodeKereta,
                                nikPenumpang,
                                namaPenumpang,
                                jumlahTiket);

                        break;

                    case 3:

                        System.out.println(
                                "Terima kasih telah menggunakan JAVA EXPRESS");

                        break;

                    default:

                        System.out.println(
                                "Menu tidak tersedia");
                }

            }

            catch (InputMismatchException errorInput) {

                System.out.println(
                        "Input harus berupa angka");

                masukanPengguna.nextLine();
            }

            catch (DataPenumpangTidakValidException errorData) {

                System.out.println(
                        errorData.getMessage());
            }

            catch (RuteTidakDitemukanException errorRute) {

                System.out.println(
                        errorRute.getMessage());
            }

            catch (TiketHabisException errorTiket) {

                System.out.println(
                        "Tiket pada kereta "
                                + errorTiket.getNamaKereta()
                                + " tidak mencukupi");

                System.out.println(
                        "Sisa kursi : "
                                + errorTiket.getSisaKursi());
            }

            catch (Exception errorLain) {

                System.out.println(
                        "Terjadi kesalahan : "
                                + errorLain.getMessage());
            }

            finally {

                System.out.println(
                        "\nProses selesai.");
            }

        } while (pilihanMenu != 3);

        masukanPengguna.close();
    }
}