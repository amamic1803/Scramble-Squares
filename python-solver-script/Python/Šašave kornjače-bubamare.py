def find_sols(polje, vrste, rezultati):
	if polje.count(0) == 0:
		rezultati.append(polje.copy())
	else:
		iskoristene = set()
		for i in polje:
			if i != 0:
				iskoristene.add(i // 10)
		trazena_pozicija = polje.index(0)
		needed_pozicije = ["", ""]
		if trazena_pozicija % 3 != 0:
			lijeva_pozicija = trazena_pozicija - 1
			kocka = vrste[polje[lijeva_pozicija] // 10].copy()
			rotacija = polje[lijeva_pozicija] % 10
			for i in range(rotacija):
				kocka.append(kocka[0])
				kocka.pop(0)
			kraj_za_spajanje = kocka[1]
			if kraj_za_spajanje[1] == "G":
				needed_pozicije[0] = kraj_za_spajanje[0] + "R"
			else:
				needed_pozicije[0] = kraj_za_spajanje[0] + "G"
		if trazena_pozicija - 3 >= 0:
			gornja_pozicija = trazena_pozicija - 3
			kocka = vrste[polje[gornja_pozicija] // 10].copy()
			rotacija = polje[gornja_pozicija] % 10
			for i in range(rotacija):
				kocka.append(kocka[0])
				kocka.pop(0)
			kraj_za_spajanje = kocka[2]
			if kraj_za_spajanje[1] == "G":
				needed_pozicije[1] = kraj_za_spajanje[0] + "R"
			else:
				needed_pozicije[1] = kraj_za_spajanje[0] + "G"
		for i in range(1, 10):
			if i not in iskoristene:
				provjerljiva_znam = vrste[i].copy()
				provjerljiva_znam.insert(0, provjerljiva_znam[-1])
				cijela_znam = "".join(provjerljiva_znam)
				trazeni_dio = "".join(needed_pozicije)
				if trazeni_dio in cijela_znam:
					if needed_pozicije[0] != "" and needed_pozicije[1] != "":
						polje[trazena_pozicija] = (i * 10) + (cijela_znam.index(trazeni_dio) // 2)
						find_sols(polje, vrste, rezultati)
					elif needed_pozicije[0] != "":
						polje[trazena_pozicija] = (i * 10) + (cijela_znam.index(trazeni_dio) // 2)
						find_sols(polje, vrste, rezultati)
					elif needed_pozicije[1] != "":
						rotate = cijela_znam.index(trazeni_dio) // 2
						rotate = rotate - 1 if rotate - 1 >= 0 else 3
						polje[trazena_pozicija] = (i * 10) + rotate
						find_sols(polje, vrste, rezultati)
					else:
						for j in range(4):
							polje[trazena_pozicija] = (i * 10) + j
							find_sols(polje, vrste, rezultati)
		polje[trazena_pozicija] = 0

def solve_turtles(vrste):
	dobro = True
	if isinstance(vrste, dict):
		dobro = False
	else:
		prihvatljive_znam = [i for i in range(1, 10)]
		for z in vrste.keys():
			if z not in prihvatljive_znam:
				dobro = False
				break
		else:
			for i in vrste.values():
				if len(i) != 4:
					dobro = False
					break
				else:
					prekid = False
					for j in i:
						if j[0] not in ["C", "P", "Z", "Ž"] or j[1] not in ["G", "R"]:
							dobro = False
							prekid = True
							break
					if prekid:
						break

	if not dobro:
		raise Exception("wrong data format")

	rezultati = []
	find_sols([0 for i in range(9)], vrste, rezultati)

	iste_znam = []
	for i in vrste.keys():
		same_digits = [i]
		for j in vrste.keys():
			if i != j and vrste[i] == vrste[j]:
				same_digits.append(j)
		same_digits.sort()
		if len(same_digits) > 1 and same_digits not in iste_znam:
			iste_znam.append(same_digits)
	for z in iste_znam:
		izbrisano = 0
		for i in range(len(rezultati)):
			for j in range(len(rezultati[i - izbrisano])):
				if rezultati[i - izbrisano][j] // 10 in z:
					rezultati[i - izbrisano][j] = z[0] * 10 + rezultati[i - izbrisano][j] % 10
			if rezultati.count(rezultati[i - izbrisano]) > 1:
				rezultati.pop(i - izbrisano)
				izbrisano += 1

	ispis = []
	while len(rezultati) != 0:
		okrenuta_kocka_1 = [[], [], []]
		okrenuta_kocka_1_final = []
		okrenuta_kocka_3 = [[], [], []]
		okrenuta_kocka_3_final = []

		for j in range(9):
			match j % 3:
				case 0:
					okrenuta_kocka_1[0].insert(0, rezultati[0][j])
					okrenuta_kocka_3[2].append(rezultati[0][j])
				case 1:
					okrenuta_kocka_1[1].insert(0, rezultati[0][j])
					okrenuta_kocka_3[1].append(rezultati[0][j])
				case 2:
					okrenuta_kocka_1[2].insert(0, rezultati[0][j])
					okrenuta_kocka_3[0].append(rezultati[0][j])
		for j in range(3):
			for z in range(3):
				okrenuta_kocka_1_final.append((okrenuta_kocka_1[j][z] // 10) * 10 + ((okrenuta_kocka_1[j][z] % 10 - 1) % 4))
				okrenuta_kocka_3_final.append((okrenuta_kocka_3[j][z] // 10) * 10 + ((okrenuta_kocka_3[j][z] % 10 - 3) % 4))

		okrenuta_kocka_2 = rezultati[0].copy()
		okrenuta_kocka_2.reverse()
		for j in range(len(okrenuta_kocka_2)):
			okrenuta_kocka_2[j] = (okrenuta_kocka_2[j] // 10) * 10 + ((okrenuta_kocka_2[j] % 10 - 2) % 4)

		ispis.append(rezultati[0])
		rezultati.pop(0)
		rezultati.pop(rezultati.index(okrenuta_kocka_1_final))
		rezultati.pop(rezultati.index(okrenuta_kocka_2))
		rezultati.pop(rezultati.index(okrenuta_kocka_3_final))

	return ispis


if __name__ == "__main__":
	# rotiranje cijelog polja ne daje novo rješenje
	# ukoliko postoje iste kartice, u rješenju se koristi broj kartice s najmanjim rednim brojem
	# prva znamenka označava broj kartice, a druga znamenka koliko puta treba karticu okrenuti u smjeru suprotnom kazaljki na satu
	# dictionary s karticama mora biti u formatu kao ispod (inače se javlja greška)
	# djelovi tijela su popisani u smjeru kazaljke na satu, tako da je prvi dio na vrhu kartice
	# prvo slovo označava početno slovo boje, a drugo slovo označava početno slovo od Glava ili Rep

	kartice = {}

	for i in range(1, 10):
		upis = input().split(" ")
		kartice[int(upis[0])] = upis[1:]

	ispis = solve_turtles(kartice)
	print(f"Broj rješenja: {len(ispis)}")
	br_rj = 0
	while len(ispis) != 0:
		br_rj += 1
		print(f"\nRješenje {br_rj}:")
		for i in range(0, 7, 3):
			print(" ".join(map(str, ispis[0][i:i + 3])))
		ispis.pop(0)
