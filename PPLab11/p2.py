import subprocess

def desparte_comenzi(input_text):

    return [segment.strip().split() for segment in input_text.split('|')]

def ruleaza_pipeline(lista_comenzi):
    procese = []
    intrare = None

    for index, args in enumerate(lista_comenzi):
        iesire = subprocess.PIPE
        if index < len(lista_comenzi) - 1
        else None

        proces = subprocess.Popen(args, stdin=intrare, stdout=iesire)
        procese.append(proces)

        if intrare:
            intrare.close()
        intrare = proces.stdout #inchide intrarea anterioare si salveaza iesirea procesului curent

    ultimul = procese[-1]
    if ultimul.stdout:
        rezultat, _ = ultimul.communicate()
        print(rezultat.decode())
    else:
        ultimul.wait()

def main():
    comanda = input("Introdu o comanda cu pipe (ex: ip a | grep inet | wc -l):\n")
    lista = desparte_comenzi(comanda)
    ruleaza_pipeline(lista)

if __name__ == "__main__":
    main()
