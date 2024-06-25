/*Queries per popolare il database*/

/*popolazione tabella turni*/

insert into turni(giornoSettimana, fasciaOraria)
values
('Lunedì','9:00-14:00'),
('Lunedì','14:00-19:00'),
('Lunedì','19:00-00:00'),
('Martedì','9:00-14:00'),
('Martedì','14:00-19:00'),
('Martedì','19:00-00:00'),
('Mercoledì','9:00-14:00'),
('Mercoledì','14:00-19:00'),
('Mercoledì','19:00-00:00'),
('Venerdì','9:00-14:00'),
('Venerdì','14:00-19:00'),
('Venerdì','19:00-00:00'),
('Sabato','9:00-14:00'),
('Sabato','14:00-19:00'),
('Sabato','19:00-00:00'),
('Domenica','9:00-14:00'),
('Domenica','14:00-19:00'),
('Domenica','19:00-00:00');

/*Popolazione tabella fornitori*/

insert into fornitori(codFornitore,nomeAzienda,recapito)
values
('F01','Lattepiù','3846180293'),
('F02','Frutta&Co','3517392910'),
('F03','ChocoFantasy','3912342310'),
('F04','SweetDreams','3418371920'),
('F05','FattoriaFelice','3713728471'),
('F06','GoingNuts','3667289142'),
('F07','ExoticFruitx','3912342310'),
('F08','WaterWater','3987182736');

/*Popolazione tabella ingredienti*/

insert into ingredienti(codIngrediente,calorie,codFornitore,nomeIngrediente)
values
('I01',60,'F01','Latte Intero'),
('I02',340,'F01','Panna Fresca'),
('I03',387,'F04','Zucchero'),
('I04',304,'F04','Miele'),
('I05',288,'F06','Estratto Vaniglia'),
('I06',228,'F03','Cacao in Polvere'),
('I07',546,'F03','Cioccolato Fondente'),
('I08',32,'F02','Fragole'),
('I09',89,'F07','Banane'),
('I10',60,'F07','Mango'),
('I11',230,'F01','Latte di Cocco'),
('I12',562,'F06','Pistacchi'),
('I13',628,'F06','Nocciole'),
('I14',59,'F01','Yogurt Greco'),
('I15',588,'F06','Burro Arachidi'),
('I16',128,'F05','Uova'),
('I17', 70,'F02', 'Ciliegie'),
('I18', 97,'F07', 'Maracuja'),
('I19', 26,'F02', 'Zucca'),
('I20', 30,'F02', 'Cocomero'),
('I21', 33, 'F02', 'Melone'),
('I22', 320,'F06', 'Canditi'),
('I23', 47,'F02', 'Arancia'),
('I24', 0,'F08', 'Acqua'),
('I25', 174, 'F01', 'Ricotta'),
('I26', 364, 'F01', 'Farina'),
('I27', 288, 'F06', 'Vaniglia'),
('I28', 275, 'F04', 'Pan di Spagna'),
('I29', 245, 'F03', 'Gocce Cioccolato');

/*popolazione tabella Prodotti*/
insert into prodotti(codProdotto,tipoProdotto,tipoGelato,numeroGusti,prezzoGelato,pesoVaschetta,prezzoVaschetta)
values
('P01','Gelato','Cono',2,3.00,NULL,NULL),
('P02','Gelato','Cono',3,3.50,NULL,NULL),
('P03','Gelato','Cono',4,4.00,NULL,NULL),
('P04','Gelato','Coppetta',2,3.00,NULL,NULL),
('P05','Gelato','Coppetta',3,3.50,NULL,NULL),
('P06','Gelato','Coppetta',4,4.00,NULL,NULL),
('P07','Vaschetta',NULL,NULL,NULL,250.00,5.50),
('P08','Vaschetta',NULL,NULL,NULL,500.000,11.00),
('P09','Vaschetta',NULL,NULL,NULL,750.00,16.50),
('P10','Vaschetta',NULL,NULL,NULL,1000.00,22.00);

/*popolazione tabella gusti*/

insert into gusti(nomeGusto, ricetta, calorieTotali, tipoGusto, disponibilita, periodoDisponibilita)
values
('Fior di Latte', 'Latte Intero, Zucchero, Panna Fresca, Estratto Vaniglia', 60 + 387 + 340 + 288, 'Base', NULL, NULL),
('Stracciatella', 'Latte Intero, Zucchero, Panna Fresca, Estratto Vaniglia, Gocce Cioccolato', 60 + 387 + 340 + 288 + 245, 'Base', NULL, NULL),
('Nocciola', 'Latte Intero, Zucchero, Panna Fresca, Nocciole', 60 + 387 + 340 + 628, 'Base', NULL, NULL),
('Crema', 'Latte Intero, Zucchero, Panna Fresca, Uova, Estratto Vaniglia', 60 + 387 + 340 + 128 + 288, 'Base', NULL, NULL),
('Cioccolato', 'Latte Intero, Zucchero, Panna Fresca, Cacao in Polvere, Cioccolato Fondente', 60 + 387 + 340 + 228 + 546, 'Base', NULL, NULL),
('Pistacchio', 'Latte Intero, Zucchero, Panna Fresca, Pistacchi', 60 + 387 + 340 + 562, 'Base', NULL, NULL),
('Limone', 'Acqua, Zucchero, Succo di Limone', 0 + 387 + 33, 'Base', NULL, NULL),
('Fragola', 'Acqua, Zucchero, Fragole', 0 + 387 + 32, 'Base', NULL, NULL),
('Pesca', 'Acqua, Zucchero, Pesca', 0 + 387 + 43, 'Stagionale', 'Si', 'Estate'),
('Cocco', 'Latte di Cocco, Zucchero', 230 + 387, 'Base', NULL, NULL),
('Tiramisu', 'Latte Intero, Zucchero, Panna Fresca, Cacao in Polvere, Pan di Spagna', 60 + 387 + 340 + 228 + 275, 'Base', NULL, NULL),
('Oreo', 'Latte Intero, Zucchero, Panna Fresca, Biscotti Oreo', 60 + 387 + 340 + 480, 'Base', NULL, NULL),
('Bacio', 'Latte Intero, Zucchero, Panna Fresca, Nocciole, Cioccolato Fondente', 60 + 387 + 340 + 628 + 546, 'Base', NULL, NULL),
('Biscotto', 'Latte Intero, Zucchero, Panna Fresca, Biscotti', 60 + 387 + 340 + 420, 'Base', NULL, NULL),
('Spagnola', 'Latte Intero, Zucchero, Panna Fresca, Ciliegie', 60 + 387 + 340 + 70, 'Base', NULL, NULL),
('Mango', 'Acqua, Zucchero, Mango', 0 + 387 + 60, 'Stagionale', 'Si', 'Estate'),
('Maracuja', 'Acqua, Zucchero, Maracuja', 0 + 387 + 97, 'Stagionale', 'Si', 'Estate'),
('Mela&Cannella', 'Acqua, Zucchero, Mela, Cannella', 0 + 387 + 52 + 5, 'Stagionale', 'No', 'Inverno'),
('Zucca', 'Acqua, Zucchero, Zucca', 0 + 387 + 26, 'Stagionale', 'No', 'Autunno'),
('Cocomero', 'Acqua, Zucchero, Cocomero', 0 + 387 + 30, 'Stagionale', 'Si', 'Estate'),
('Melone', 'Acqua, Zucchero, Melone', 0 + 387 + 33, 'Stagionale', 'Si', 'Estate'),
('Banana', 'Acqua, Zucchero, Banane', 0 + 387 + 89, 'Stagionale', 'No', 'Primavera'),
('Cassata', 'Latte Intero, Zucchero, Ricotta, Canditi', 60 + 387 + 174 + 320, 'Stagionale', 'Si', 'Estate'),
('Kinder', 'Latte Intero, Zucchero, Panna Fresca, Cioccolato al Latte', 60 + 387 + 340 + 528, 'Base', NULL, NULL),
('Zuppa Inglese', 'Latte Intero, Zucchero, Panna Fresca, Pan di Spagna', 60 + 387 + 340 + 275, 'Stagionale', 'No', 'Inverno'),
('Pera&Formaggio', 'Latte Intero, Zucchero, Ricotta, Pere', 60 + 387 + 174 + 50, 'Stagionale', 'No', 'Estate'),
('Mirtillo', 'Acqua, Zucchero, Mirtilli', 0 + 387 + 57, 'Stagionale', 'No', 'Primavera'),
('Castagna', 'Acqua, Zucchero, Castagne', 0 + 387 + 210, 'Stagionale', 'No', 'Autunno'),
('Arancia', 'Acqua, Zucchero, Succo di Arancia', 0 + 387 + 47, 'Stagionale', 'No', 'Inverno');


/*popolazione tabella costituzioni*/
insert into costituzioni(nomeGusto, codIngrediente)
values
('Fior di Latte', 'I01'),
('Fior di Latte', 'I03'),
('Fior di Latte', 'I02'),
('Fior di Latte', 'I27'),
('Stracciatella', 'I01'),
('Stracciatella', 'I03'),
('Stracciatella', 'I02'),
('Stracciatella', 'I27'),
('Stracciatella', 'I29'),
('Nocciola', 'I01'),
('Nocciola', 'I03'),
('Nocciola', 'I02'),
('Nocciola', 'I13'),
('Crema', 'I01'),
('Crema', 'I03'),
('Crema', 'I02'),
('Crema', 'I16'),
('Crema', 'I27'),
('Cioccolato', 'I01'),
('Cioccolato', 'I03'),
('Cioccolato', 'I02'),
('Cioccolato', 'I06'),
('Cioccolato', 'I07'),
('Pistacchio', 'I01'),
('Pistacchio', 'I03'),
('Pistacchio', 'I02'),
('Pistacchio', 'I12'),
('Limone', 'I24'),
('Limone', 'I03'),
('Limone', 'I23'),
('Fragola', 'I24'),
('Fragola', 'I03'),
('Fragola', 'I08'),
('Pesca', 'I24'),
('Pesca', 'I03'),
('Pesca', 'I26'),
('Cocco', 'I11'),
('Cocco', 'I03'),
('Tiramisu', 'I01'),
('Tiramisu', 'I03'),
('Tiramisu', 'I02'),
('Tiramisu', 'I06'),
('Tiramisu', 'I28'),
('Oreo', 'I01'),
('Oreo', 'I03'),
('Oreo', 'I02'),
('Oreo', 'I29'),
('Bacio', 'I01'),
('Bacio', 'I03'),
('Bacio', 'I02'),
('Bacio', 'I13'),
('Bacio', 'I07'),
('Biscotto', 'I01'),
('Biscotto', 'I03'),
('Biscotto', 'I02'),
('Spagnola', 'I01'),
('Spagnola', 'I03'),
('Spagnola', 'I02'),
('Spagnola', 'I17'),
('Mango', 'I24'),
('Mango', 'I03'),
('Mango', 'I10'),
('Maracuja', 'I24'),
('Maracuja', 'I03'),
('Maracuja', 'I18'),
('Mela&Cannella', 'I24'),
('Mela&Cannella', 'I03'),
('Mela&Cannella', 'I26'),
('Zucca', 'I24'),
('Zucca', 'I03'),
('Zucca', 'I19'),
('Cocomero', 'I24'),
('Cocomero', 'I03'),
('Cocomero', 'I20'),
('Melone', 'I24'),
('Melone', 'I03'),
('Melone', 'I21'),
('Banana', 'I24'),
('Banana', 'I03'),
('Banana', 'I09'),
('Cassata', 'I01'),
('Cassata', 'I03'),
('Cassata', 'I25'),
('Cassata', 'I22'),
('Kinder', 'I01'),
('Kinder', 'I03'),
('Kinder', 'I02'),
('Kinder', 'I29'),
('Zuppa Inglese', 'I01'),
('Zuppa Inglese', 'I03'),
('Zuppa Inglese', 'I02'),
('Zuppa Inglese', 'I28'),
('Pera&Formaggio', 'I01'),
('Pera&Formaggio', 'I03'),
('Pera&Formaggio', 'I25'),
('Pera&Formaggio', 'I26'),
('Mirtillo', 'I24'),
('Mirtillo', 'I03'),
('Mirtillo', 'I26'),
('Castagna', 'I24'),
('Castagna', 'I03'),
('Castagna', 'I26'),
('Arancia', 'I24'),
('Arancia', 'I03'),
('Arancia', 'I23');


/*popolazione tabella dipendenti*/

insert into dipendenti(CF, nome, cognome, dataNascita, password, IBAN, stipendio, dataAssunzione, dataLicenziamento)
values
('D001','Mario','Rossi','1990-01-15','11111','T88D0300203280839676919588',0,'2023-05-14',NULL),
('D002','Maria','De Filippi','1979-04-18','22222','IT07I0300203280943453955222',0,'2022-08-18',NULL),
('D003','Gerry','Scotti','1998-11-11','33333','IT18I0300203280492439717372', 0,'2023-09-12',NULL),
('D004','Michele','Bianchi','1999-10-29','44444','IT71G0300203280581298455446',0,'2022-04-15','2024-05-19'),
('D005','Lisa','Lorenzini','2000-08-12','55555','IT59E0300203280665339469597',0,'2023-07-10','2024-06-15'),
('D006','Franco','Battiato','1990-02-26','66666','IT72D0300203280997695894397',0,'2022-01-12','2024-06-12'),
('D007','Gisella','Pasquali','1998-03-27','77777','IT28C0300203280662325842583',0,'2023-07-14',NULL),
('D008','Giada','Alfieri','2003-04-15','88888','IT25T0300203280173892547679',0,'2023-01-17',NULL);


/*popolazione tabella partecipazioni*/

insert into partecipazioni(CF,giornoSettimana,fasciaOraria)
values
('D001','Lunedì','9:00-14:00'),
('D001','Martedì','9:00-14:00'),
('D001','Mercoledì','9:00-14:00'),
('D001','Venerdì','9:00-14:00'),
('D001','Sabato','9:00-14:00'),
('D001','Domenica','9:00-14:00'),
('D001','Lunedì','19:00-00:00'),
('D002','Lunedì','14:00-19:00'),
('D002','Martedì','14:00-19:00'),
('D002','Martedì','19:00-00:00'),
('D002','Mercoledì','14:00-19:00'),
('D002','Venerdì','14:00-19:00'),
('D002','Sabato','14:00-19:00'),
('D002','Domenica','14:00-19:00'),
('D002','Domenica','9:00-14:00'),
('D003','Lunedì','19:00-00:00'),
('D003','Martedì','19:00-00:00'),
('D003','Mercoledì','19:00-00:00'),
('D003','Mercoledì','14:00-19:00'),
('D003','Venerdì','19:00-00:00'),
('D003','Sabato','19:00-00:00'),
('D003','Domenica','19:00-00:00'),
('D007','Martedì','9:00-14:00'),
('D007','Venerdì','9:00-14:00'),
('D007','Sabato','14:00-19:00'),
('D007','Sabato','19:00-00:00'),
('D007','Domenica','19:00-00:00'),
('D007','Domenica','14:00-19:00'),
('D008','Lunedì','9:00-14:00'),
('D008','Martedì','14:00-19:00'),
('D008','Venerdì','19:00-00:00'),
('D008','Venerdì','14:00-19:00'),
('D008','Sabato','9:00-14:00'),
('D008','Sabato','19:00-00:00'),
('D008','Domenica','19:00-00:00');

/*popolazione tabella clienti*/

insert into clienti(CF,nome,cognome,dataNascita,e_mail,dataIscrizione,dataDisiscrizione,Reg_CF)
values
('C001', 'Luca', 'Rossi', '1990-05-15', 'luca.rossi@example.com', '2022-03-10', NULL, 'D006'),
('C002', 'Giulia', 'Bianchi', '1985-08-22', 'giulia.bianchi@example.com', '2022-06-18', '2023-07-19', 'D004'),
('C003', 'Marco', 'Ferrari', '1978-11-30', 'marco.ferrari@example.com', '2022-07-25', NULL, 'D004'),
('C004', 'Anna', 'Conti', '1992-02-12', 'anna.conti@example.com', '2022-09-05', '2023-08-14', 'D002'),
('C005', 'Davide', 'Ricci', '1983-04-28', NULL, '2022-11-10', NULL, 'D004'),
('C006', 'Sara', 'Marini', '1995-01-17', 'sara.marini@example.com', '2023-01-14', NULL, 'D002'),
('C007', 'Francesco', 'Costa', '1988-07-05', 'francesco.costa@example.com', '2023-02-21', NULL, 'D002'),
('C008', 'Valentina', 'Galli', '1991-10-03', 'valentina.galli@example.com', '2023-03-30', NULL, 'D008'),
('C009', 'Matteo', 'Fontana', '1986-09-20', 'matteo.fontana@example.com', '2023-05-17', NULL, 'D002'),
('C010', 'Elena', 'Greco', '1994-06-11', NULL, '2023-06-25', NULL, 'D008');


/*popolazione tabella tessere*/

insert into tessere(CF, numeroTessera, numeroAcquisti)
values
('C001', 1, 0),
('C002', 1, 0),
('C003', 1, 0),
('C004', 1, 0),
('C005', 1, 0),
('C006', 1, 0),
('C007', 1, 0),
('C008', 1, 0),
('C009', 1, 0),
('C010', 1, 0);


