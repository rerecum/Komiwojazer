# CitiesTravelling

## Założenia projektu:
### Aplikacja będzie pokazywać najlepszą ścieżkę pomiędzy miastami przy jednoczesnym odwiedzaniu każdego z miast tylko raz.

## Funkcjonalności

- Aplikacja umożliwia wprowadzenie ośmiu miast, które będą uwzględnione w obliczeniach.

- Użytkownik może wybrać dowolne miasto jako punkt startowy oraz dowolne miasto jako punkt końcowy.

- Aplikacja oblicza i wyświetla najkrótszą ścieżkę pomiędzy wybranymi miastami, wraz z listą miast, które należy przejść oraz całkowitym dystansem do pokonania.

- Aplikacja umożliwia również edycję nazw miast oraz długości dróg łączących je.

- Aplikacja automatycznie uzupełnia dane, w przypadku braku ich uzupełnienia przez użytkownika.

## Wygląd


- Aplikacja posiada przyjazny dla użytkownika interfejs, w którym wszystkie opcje są intuicyjnie dostępne.

- Ekran główny zawiera łatwo dostępne pola do wpisania danych.

- Przycisk służący do uruchomienia głównego skryptu programu jest łatwo dostępny i zauważalny od razu po uruchomieniu aplikacji.

- W aplikacji zastosowano estetyczne i spójne ikony oraz grafiki.

- ### Schemat:
```mermaid
flowchart TD;
      A[Pole do wprowadzenia danych]-->B["Oddzielne pola edycyjne dla każdego z miast"];
    A-->D["Pola edycyjne dla dróg każdego z miast np: Miasto A -> Miasto B"];
    B & D --> C["Przycisk do uruchomienia wyszukiwania"];
    C --> E["Graficzna Reprezentacja obrazu"] & F["Przycisk powrotu"];
    F --> A;
```


## Rozwiązania

- Do obliczenia najkrótszej ścieżki między miastami zostanie wykorzystany Travelling Salesman Problem.

- Aplikacja będzie używała Canvas w celu rysowania graficznej reprezentacji miast oraz ścieżek pomiędzy nimi.

- Aplikacja będzie używała klasy MediaScannerConnection w celu zapisywania wygenerowanych graficznych reprezentacji ścieżek do galerii zdjęć w osobnym albumie.

- Aplikacja będzie używała powiadomień w celu potwierdzenia zapisania zdjęć.

- W celu zwiększenia wydajności i szybkości działania aplikacji, zostanie zastosowana optymalizacja kodu oraz odpowiednie struktury danych.

## Ścieżka miast

```mermaid
flowchart TD;
      A1<-->A2
      A1<-->A3
      A1<-->A4
      A1<-->A5
      A1<-->A6
      A1<-->A7
      A1<-->A8
      A2<-->A3
      A2<-->A4
      A2<-->A5
      A2<-->A6
      A2<-->A7
      A2<-->A8
      A3<-->A4
      A3<-->A5
      A3<-->A6
      A3<-->A7
      A3<-->A8
      A4<-->A5
      A4<-->A6
      A4<-->A7
      A4<-->A8
      A5<-->A6
      A5<-->A7
      A5<-->A8
      A6<-->A7
      A6<-->A8
      A7<-->A8

```