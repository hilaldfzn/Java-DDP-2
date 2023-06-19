package Lab06;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.*;

public class Starcil {
  // Inisiasi array daftarMinuman untuk menyimpan jenis minuman
  private static InputReader in;
  private static PrintWriter out;
  private static Beverage[] daftarMinuman = new Beverage[0];

  // Method untuk memasukkan minuman ke dalam array daftar minuman yang baru
  private static void masukkanKeDaftarMinuman(Beverage beverage) {
    Beverage[] newDaftarMinuman = new Beverage[daftarMinuman.length + 1];

    // Melakukan indexing untuk tiap minuman yang ditambahkan ke array
    for (int i = 0; i < daftarMinuman.length; i++) {
      newDaftarMinuman[i] = daftarMinuman[i];
    }

    daftarMinuman = newDaftarMinuman;
    newDaftarMinuman[daftarMinuman.length - 1] = beverage;
  }

  // Method untuk mengambil minuman dari array daftarMinuman
  private static Beverage getMinuman(String namaMinuman) {
    for (Beverage drink : daftarMinuman) {
      if (drink.getName().equalsIgnoreCase(namaMinuman)) {
        return drink;
      }
    }
    return null;
  }

  public void mainProgram() {
    InputStream inputStream = System.in;
    in = new InputReader(inputStream);
    OutputStream outputStream = System.out;
    out = new PrintWriter(outputStream);

    int N;
    N = in.nextInt();                       // Input untuk banyak perintah yang akan dilakukan
    for (int i = 0; i < N; i++) {
      String event = in.next();             // Input untuk jenis perintah yang akan dilakukan

      /* Jika perintah ADD, maka formatnya: ADD [JENIS] [NAMA] [UKURAN] [ES]
       * Perintah ini untuk menambahkan minuman ke dalam array daftarMinuman
       * Note: Nama dijamin bersifat unik
       */
      if (event.equals("ADD")) {
        String jenisMinuman = in.next();
        String nama = in.next();
        String size = in.next();
        boolean isCold = in.next().equals("YES");

        // Asumsikan input jenis minuman dijamin pasti valid, yaitu "COFFEE" dan "TEA"
        if (jenisMinuman.equals("COFFEE")) {
          Coffee coffee = new Coffee(nama, size, isCold);   // Inisiasi object Coffee
          masukkanKeDaftarMinuman(coffee);                  // Menambahkan coffee ke array daftarMinuman
        } else {
          Tea tea = new Tea(nama, size, isCold);            // Inisiasi object Tea
          masukkanKeDaftarMinuman(tea);                     // Menambahkan tea ke array daftarMinuman
        }
      }
      
      /* Jika perintah TOPPING, maka formatnya: TOPPING [TOPPING]
       * Perintah ini untuk menambahkan topping ke minuman, topping
       * akan menyesuaikan dengan jenis minuman yang dipilih
       */
      else if (event.equals("TOPPING")) {
        String namaMinuman = in.next();
        Beverage minuman = getMinuman(namaMinuman);
        
        if (minuman instanceof Coffee) {
          Coffee coffee = (Coffee) minuman;
          coffee.addWhipCream();
        } else if (minuman instanceof Tea) {
          Tea tea = (Tea) minuman;
          tea.addMilk();
        }
      }

      /* Jika perintah ORDERAN, maka formatnya: ORDERAN [JENIS]
       * Perintah ini untuk mengeluarkan semua orderan dengan
       * jenis [JENIS] yang ada di sistem
       */
      else if (event.equals("ORDERAN")) {
        String jenisMinuman = in.next();

        for (int j = 0; j < daftarMinuman.length; j++) {
          if (jenisMinuman.equalsIgnoreCase("COFFEE") && daftarMinuman[j] instanceof Coffee) {
            out.println(daftarMinuman[j]);                  // Mencetak semua minuman yang berjenis COFFEE
          } else if (jenisMinuman.equalsIgnoreCase("TEA") && daftarMinuman[j] instanceof Tea) {
            out.println(daftarMinuman[j]);                  // Mencetak semua minuman yang berjenis TEA
          }
        }
      }

      // Jika perintah selain ADD, TOPPING, dan ORDERAN, maka akan mencetak pesan error
      else {
        out.println("PERINTAH TIDAK DITEMUKAN");
      }
    }
    out.flush();
  }

  public static void main(String[] args) {
    Starcil cafe = new Starcil();
    cafe.mainProgram();
  }

  // taken from https://codeforces.com/submissions/Petr
  // together with PrintWriter, these input-output (IO) is much faster than the
  // usual Scanner(System.in) and System.out
  // please use these classes to avoid your fast algorithm gets Time Limit
  // Exceeded caused by slow input-output (IO)
  static class InputReader {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
      reader = new BufferedReader(new InputStreamReader(stream), 32768);
      tokenizer = null;
    }

    public String next() {
      while (tokenizer == null || !tokenizer.hasMoreTokens()) {
        try {
          tokenizer = new StringTokenizer(reader.readLine());
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
      return tokenizer.nextToken();
    }

    public int nextInt() {
      return Integer.parseInt(next());
    }
  }
}
