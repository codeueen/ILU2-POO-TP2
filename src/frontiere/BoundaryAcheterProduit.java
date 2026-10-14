package frontiere;

import controleur.ControlAcheterProduit;

public class BoundaryAcheterProduit {
	private ControlAcheterProduit controlAcheterProduit;

	public BoundaryAcheterProduit(ControlAcheterProduit controlAcheterProduit) {
		this.controlAcheterProduit = controlAcheterProduit;
	}

	public void acheterProduit(String nomAcheteur) {
		if (!controlAcheterProduit.verifierIdentite(nomAcheteur)) {
			System.out.println("Je suis désolée " + nomAcheteur
					+ " mais il faut être un habitant de notre village pour commercer ici.");
		} else {
			String produit = Clavier.entrerChaine("Quel produit voulez-vous acheter ?");
			String[] vendeurs = controlAcheterProduit.rechercherVendeursProduit(produit);
			if (vendeurs == null) {
				System.out.println("Désolé, personne ne vend ce produit au marché.");
			} else {
				StringBuilder question = new StringBuilder();
				question.append("Chez quel commerçant voulez-vous acheter des "
						+ produit + " ?\n");
				for (int i = 0; i < vendeurs.length; i++) {
					question.append((i + 1) + " - " + vendeurs[i] + "\n");
				}
				int choix = 0;
				do {
					choix = Clavier.entrerEntier(question.toString());
				} while (choix < 1 || choix > vendeurs.length);
				String nomVendeur = vendeurs[choix - 1];
				acheterChezVendeur(nomAcheteur, nomVendeur, produit);
			}
		}
	}

	private void acheterChezVendeur(String nomAcheteur, String nomVendeur,
			String produit) {
		if (!controlAcheterProduit.verifierIdentite(nomVendeur)) {
			System.out.println("Je suis désolée " + nomVendeur
					+ " mais il faut être un habitant de notre village pour commercer ici.");
		} else {
			System.out.println(nomAcheteur
					+ " se déplace jusqu'à l'étal du vendeur " + nomVendeur);
			System.out.println("Bonjour " + nomAcheteur);
			int quantiteVoulue = Clavier.entrerEntier(
					"Combien de " + produit + " voulez-vous acheter ?");
			int quantiteAchetee = controlAcheterProduit
					.acheterProduit(nomVendeur, quantiteVoulue);
			if (quantiteAchetee == 0) {
				System.out.println(nomAcheteur + " veut acheter " + quantiteVoulue
						+ " " + produit + ", malheureusement il n'y en a plus !");
			} else if (quantiteAchetee < quantiteVoulue) {
				System.out.println(nomAcheteur + " veut acheter " + quantiteVoulue
						+ " " + produit + ", malheureusement " + nomVendeur
						+ " n'en a plus que " + quantiteAchetee + ". " + nomAcheteur
						+ " achète tout le stock de " + nomVendeur + ".");
			} else {
				System.out.println(nomAcheteur + " achète " + quantiteVoulue + " "
						+ produit + " à " + nomVendeur + ".");
			}
		}
	}
}