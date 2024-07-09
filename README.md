# technical_tests

Project untuk menyelesaikan take home test.
Jawaban logical dan practical tests dibagi menjadi endpoint API masing-masing.

## Logical Tests:

1.  Convert to Readable
    Endpoint: /readable
    Method: GET
    Parameters: s (String) - string yang ingin di convert
    Response: String yang mudah dibaca
    Example: http://localhost:8080/readable?s=iadab%20itsap%20ulalreb

2.  FizzBuzz
    Endpoint: /fizzbuzz
    Method: GET
    Response: Serangkaian angka dari 1 hingga 100 dengan penerapan aturan FizzBuzz
    Example: http://localhost:8080/fizzbuzz

3.  Fibonacci
    Endpoint: /fibonacci
    Method: GET
    Parameters: n (int) - jumlah angka Fibonacci yang ingin dibuat
    Response: Daftar angka Fibonacci
    Example: http://localhost:8080/fibonacci?n=9

4.  Max Profit
    Endpoint: /maxProfit
    Method: GET
    Parameters: prices (int[]) - array dari harga saham
    Response: Angka pengambilan nilai saham yang menghasilkan keuntungan terbesar
    Example: http://localhost:8080/maxProfit?prices=10,9,6,5,15

5.  Count Numbers
    Endpoint: /countNumbers
    Method: GET
    Parameters: str (List<String>) - list string yang ingin dihitung jumlah angkanya
    Response: Jumlah angka dalam daftar string
    Example: http://localhost:8080/countNumbers?str=2,h,6,u,y,t,7,j,y,h,8

## Practical Tests:

Summary pemesanan ruangan berdasarkan data transaksi yang ada untuk digunakan pada halaman dashboard.
Endpoint: /summary
Method: GET
Response: Data summary pemesanan ruangan berdasarkan bulan dan unit/officenya
Example: http://localhost:8080/summary
