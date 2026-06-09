public class ValidatorPenumpang {

    /*
     * SOLID - SRP
     * Class ini hanya untuk validasi NIK.
     */

    public void validasiNik(String nikPenumpang) {

        if (!nikPenumpang.matches("\\d{16}")) {

            throw new DataPenumpangTidakValidException(
                    "NIK harus terdiri dari 16 digit angka");
        }
    }
}// SRP
// Tanggung jawab class ini hanya validasi data penumpang.
// Tidak menyimpan data kereta dan tidak melakukan reservasi.