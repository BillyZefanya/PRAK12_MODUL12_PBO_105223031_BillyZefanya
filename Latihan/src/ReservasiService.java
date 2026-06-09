public class ReservasiService {

    /*
     * SOLID - SRP
     * Class ini hanya mengurus reservasi tiket.
     */

    private RepositoryKereta repositoryKereta;
    private ValidatorPenumpang validatorPenumpang;

    public ReservasiService(
            RepositoryKereta repositoryKereta,
            ValidatorPenumpang validatorPenumpang) {

        /*
         * SOLID - DIP
         * Dependency dikirim dari luar
         * melalui constructor.
         */

        this.repositoryKereta = repositoryKereta;
        this.validatorPenumpang = validatorPenumpang;
    }

    public void tampilkanJadwal() {

        System.out.println("\n===== DAFTAR KERETA =====");

        for (KeretaApi kereta :
                repositoryKereta.getDaftarKereta()) {

            System.out.println(
                    "Kode       : "
                            + kereta.getKodeKereta());

            System.out.println(
                    "Nama       : "
                            + kereta.getNamaKereta());

            System.out.println(
                    "Rute       : "
                            + kereta.getRutePerjalanan());

            System.out.println(
                    "Sisa Kursi : "
                            + kereta.getSisaKursi());

            System.out.println();
        }
    }

    public void pesanTiket(
            String kodeKereta,
            String nikPenumpang,
            String namaPenumpang,
            int jumlahTiket)

            throws RuteTidakDitemukanException,
            TiketHabisException {

        validatorPenumpang
                .validasiNik(nikPenumpang);

        KeretaApi kereta =
                repositoryKereta
                        .cariKereta(kodeKereta);

        if (jumlahTiket >
                kereta.getSisaKursi()) {

            throw new TiketHabisException(
                    kereta.getNamaKereta(),
                    kereta.getSisaKursi());
        }

        kereta.setSisaKursi(
                kereta.getSisaKursi()
                        - jumlahTiket);

        System.out.println(
                "\n===== PEMESANAN BERHASIL =====");

        System.out.println(
                "Nama Penumpang : "
                        + namaPenumpang);

        System.out.println(
                "NIK            : "
                        + nikPenumpang);

        System.out.println(
                "Kereta         : "
                        + kereta.getNamaKereta());

        System.out.println(
                "Jumlah Tiket   : "
                        + jumlahTiket);
    }
}// SRP
// Class hanya mengurus reservasi tiket.

// DIP
// RepositoryKereta dan ValidatorPenumpang
// tidak dibuat di dalam class,
// tetapi diterima melalui constructor.