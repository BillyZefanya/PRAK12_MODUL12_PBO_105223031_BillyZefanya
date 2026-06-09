interface PencarianBuku {
    Buku cariBerdasarkanJudul(String judul);
}

interface LayananPeminjaman {
    void pinjamBuku(Buku buku, Anggota anggota);
}

interface PerhitunganDenda {
    double hitungDenda(int jumlahHariTerlambat);
}

interface FormatterLaporanDenda {
    String formatLaporan(double jumlahDenda);
}

class Buku {
    private String judul;

    public Buku(String judul) {
        this.judul = judul;
    }

    public String getJudul() {
        return judul;
    }
}

class Anggota {
    private String nama;

    public Anggota(String nama) {
        this.nama = nama;
    }

    public String getNama() {
        return nama;
    }
}

class PencarianBukuPerpustakaan implements PencarianBuku {

    @Override
    public Buku cariBerdasarkanJudul(String judul) {
        return new Buku(judul);
    }
}

class LayananPeminjamanPerpustakaan implements LayananPeminjaman {

    @Override
    public void pinjamBuku(Buku buku, Anggota anggota) {
        System.out.println(
                anggota.getNama() +
                " meminjam buku " +
                buku.getJudul()
        );
    }
}

class PerhitunganDendaStandar implements PerhitunganDenda {

    private final double tarifDendaPerHari = 5000;

    @Override
    public double hitungDenda(int jumlahHariTerlambat) {
        return jumlahHariTerlambat * tarifDendaPerHari;
    }
}

class FormatterTeksBiasa implements FormatterLaporanDenda {

    @Override
    public String formatLaporan(double jumlahDenda) {
        return "Jumlah denda: Rp " + jumlahDenda;
    }
}

class FormatterTabel implements FormatterLaporanDenda {

    @Override
    public String formatLaporan(double jumlahDenda) {
        return "====================\n"
                + "LAPORAN DENDA\n"
                + "====================\n"
                + "Jumlah Denda : Rp " + jumlahDenda;
    }
}

class ManajerPerpustakaan {

    private PencarianBuku pencarianBuku;
    private LayananPeminjaman layananPeminjaman;
    private PerhitunganDenda perhitunganDenda;
    private FormatterLaporanDenda formatterLaporanDenda;

    public ManajerPerpustakaan(
            PencarianBuku pencarianBuku,
            LayananPeminjaman layananPeminjaman,
            PerhitunganDenda perhitunganDenda,
            FormatterLaporanDenda formatterLaporanDenda) {

        this.pencarianBuku = pencarianBuku;
        this.layananPeminjaman = layananPeminjaman;
        this.perhitunganDenda = perhitunganDenda;
        this.formatterLaporanDenda = formatterLaporanDenda;
    }

    public Buku cariBuku(String judul) {
        return pencarianBuku.cariBerdasarkanJudul(judul);
    }

    public void pinjamBuku(Buku buku, Anggota anggota) {
        layananPeminjaman.pinjamBuku(buku, anggota);
    }

    public double hitungDenda(int jumlahHariTerlambat) {
        return perhitunganDenda.hitungDenda(jumlahHariTerlambat);
    }

    public String buatLaporanDenda(double jumlahDenda) {
        return formatterLaporanDenda.formatLaporan(jumlahDenda);
    }
}

public class Main {
    public static void main(String[] args) {

        PencarianBuku pencarianBuku =
                new PencarianBukuPerpustakaan();

        LayananPeminjaman layananPeminjaman =
                new LayananPeminjamanPerpustakaan();

        PerhitunganDenda perhitunganDenda =
                new PerhitunganDendaStandar();

        FormatterLaporanDenda formatterLaporanDenda =
                new FormatterTabel();

        ManajerPerpustakaan manajerPerpustakaan =
                new ManajerPerpustakaan(
                        pencarianBuku,
                        layananPeminjaman,
                        perhitunganDenda,
                        formatterLaporanDenda
                );

        Buku buku =
                manajerPerpustakaan.cariBuku(
                        "Pemrograman Berorientasi Objek"
                );

        Anggota anggota =
                new Anggota("Billy Zefanya");

        manajerPerpustakaan.pinjamBuku(
                buku,
                anggota
        );

        double jumlahDenda =
                manajerPerpustakaan.hitungDenda(3);

        String laporan =
                manajerPerpustakaan.buatLaporanDenda(
                        jumlahDenda
                );

        System.out.println(laporan);
    }
}