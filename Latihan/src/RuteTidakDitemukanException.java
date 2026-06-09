public class RuteTidakDitemukanException extends Exception {

    public RuteTidakDitemukanException(String pesan) {
        super(pesan);
    }

} // SRP
// Class ini hanya bertugas menangani
// exception ketika kode kereta tidak ditemukan.