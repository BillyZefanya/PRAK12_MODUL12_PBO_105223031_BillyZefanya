import java.util.ArrayList;
import java.util.List;

public class RepositoryKereta {

    /*
     * SOLID - SRP
     * Class ini hanya mengelola data kereta.
     *
     * SOLID - DIP
     * Menggunakan List agar tidak bergantung
     * langsung pada ArrayList.
     */

    private List<KeretaApi> daftarKereta;

    public RepositoryKereta() {

        daftarKereta = new ArrayList<>();

        daftarKereta.add(
                new KeretaApi(
                        "K01",
                        "Argo Bromo",
                        "JKT - SBY",
                        50));

        daftarKereta.add(
                new KeretaApi(
                        "K02",
                        "Parahyangan",
                        "JKT - BDG",
                        15));
    }

    public List<KeretaApi> getDaftarKereta() {
        return daftarKereta;
    }

    public KeretaApi cariKereta(
            String kodeKereta)
            throws RuteTidakDitemukanException {

        for (KeretaApi kereta : daftarKereta) {

            if (kereta.getKodeKereta()
                    .equalsIgnoreCase(kodeKereta)) {

                return kereta;
            }
        }

        throw new RuteTidakDitemukanException(
                "Kode kereta tidak ditemukan");
    }
}
// SRP
// Class hanya menyimpan dan mencari data kereta.

// DIP
// Menggunakan List bukan ArrayList
// sehingga tidak bergantung pada implementasi tertentu.