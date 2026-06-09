public class DataPenumpangTidakValidException extends RuntimeException {

    public DataPenumpangTidakValidException(String pesan) {
        super(pesan);
    }

} // SRP
// Class ini hanya bertugas menangani
// exception data penumpang tidak valid.