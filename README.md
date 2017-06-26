# SterowanieSprezarka
Program reguluje systemem zabezpieczającym,  który powinien wyłączyć napięcie zasilające sprężarkę,  jeżeli napięcie w sieci jest mniejsze niż U1(górna granica) lub większe niż U2(dolna granica);
Dla realizacji właściwej symulacji wahania napięcia w sieci program wykorzystuje generator liczb losowych.
Generator ten znajduje się w podstawowych bibliotekach Java i nie wymaga żadnych dodatkowych zmian.
W przypadku chwilowego zaniku napięcia sieci program wysteruje systemem zabezpieczającym tak, aby ponowne włączenie zasilania sprężarki nastąpiło nie wcześniej niż po upływie t sekund liczonych od pojawienia się zaniku napięcia sieci. 
