# [Unrated] Górnicy - 8859 

[문제 링크](https://www.acmicpc.net/problem/8859) 

### 성능 요약

메모리: 139600 KB, 시간: 2128 ms

### 분류

Empty

### 문제 설명

<p>SBP (Super Bogate Państwo) swoje bogactwo zawdzięcza między innymi własnym złożom złota. Chcąc zwiększyć poziom wydobycia kruszca władze państwa postanowiły wprowadzić na stałe przydział górników do odpowiednich tuneli.</p>

<p>Wszystkie kopalnie SBP są zbudowane według tego samego schematu. Każda kopalnia ma dokładnie jedno wejście i składa się z komór oraz łączących je tuneli. Do każdej z komór prowadzi dokładnie jedna trasa (być może przechodząca wieloma tunelami poprzez inne komory) z wejścia do kopalni. Wydobycie złota odbywa się w tylko komorach połączonych z dokładnie jedną inną komorą. Wiadomo także, że złota nie wydobywa się w komorze wejściowej (nawet jeśli ta łączy się z tylko jedną inną komorą.) Tunele, którymi połączone są komory mają różne wysokości. Górnicy obładowani sprzętem nie mogą się schylać, dlatego też nie zawsze wiadomo czy dany górnik może dojść do wyznaczonej dla niego komory.</p>

<p>Twoim zadaniem jest pomóc władzom SBP poprzez napisanie programu, który wczyta rozkład komór i tuneli w kopalniach, wzrost każdego z górników i poda ilu maksymalnie górników jest w stanie wydobywać złoto jednocześnie. W pojedynczej komorze może pracować tylko jeden górnik.</p>

### 입력 

 <p>W pierwszej linii wejścia znajduje się pojedyncza liczba <strong>T </strong>(1<=<strong>T</strong><=5) oznaczająca liczbę zestawów danych. W kolejnych liniach znajdują się opisy zestawów danych. Pierwsza linia opisu zestawu danych zawiera dwie liczby całkowite <strong>n</strong>, <strong>k</strong> (3<=<strong>n</strong><=200000, 1<=<strong>k</strong><=<strong>n</strong>) oznaczające liczbę komór w kopalni (komory są ponumerowane od 1 do <strong>n</strong>) oraz numer komory wejściowej. W kolejnych <strong>n</strong>-1 liniach znajdują się opisy tuneli. Pojedynczy opis tunelu składa z trzech liczb całkowitych <strong>a</strong>,<strong>b</strong>,<strong>c</strong> (1<=<strong>a</strong><<strong>b</strong><=<strong>n</strong>, 1<=<strong>c</strong><=1000). Liczby te oznaczają, że komory o numerach<strong> a</strong> i <strong>b</strong> są połączone tunelem o wysokości <strong>c</strong>. Żadna para nie pojawi się na wejściu więcej niż jeden raz. Kolejny wiersz opisu danych zawiera jedną liczbę całkowitą <strong>m</strong> (1<=<strong>m</strong><=200000) oznaczającą liczbę górników pracujących dla tej kopalni. Następny wiersz zawiera <strong>m</strong> liczb dodatnich nie większych od 1000 oznaczających wzrosty kolejnych górników.</p>

### 출력 

 <p>Twój program powinien wypisać <strong>T</strong> linii. W i-tej z nich powinna znajdować się odpowiedź dla i-tego zestawu danych - maksymalna liczba górników, która może jednocześnie pracować w kopalni. Górnicy mogą przechodzić tylko tunelami o wysokości większej lub równej swojemu wzrostowi.</p>

