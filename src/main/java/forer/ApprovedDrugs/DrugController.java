package forer.ApprovedDrugs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.swing.JLabel;
import com.google.inject.Inject;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class DrugController {

    private DrugView view;
    private DrugService service;
    DrugFeed feed;
    private Disposable disposable;

    @Inject
    public DrugController(DrugService service, DrugView view) {
        this.view = view;
        this.service = service;
    }

    public void requestDrugFeed() {
            disposable = Observable.interval(0,10000, TimeUnit.SECONDS)
                    .flatMap(aLong -> service.getApprovedDrugs())
                    .map(DrugFeed::getMolecules)
                    .subscribeOn(Schedulers.io())
                    .observeOn(Schedulers.single())
                    .subscribe(this :: setMolecules,
                               throwable -> System.out.println("Error getting data"));

        }

    private void setMolecules(List<Molecule> list) {
        for (Molecule mol : list) {
            view.getDrugIDs().addItem(mol.getMolId());
        }
    }

    public void fillInData(String selectedItem, JLabel name, JLabel formula, JLabel weight, JLabel species,
                           JLabel rings, JLabel image) throws IOException {
        Molecule mol = feed.getMolecule(selectedItem);
        name.setText(mol.getName());
        formula.setText(mol.getProperties().getFormula());
        weight.setText(mol.getProperties().getWeight());
        species.setText(mol.getProperties().getSpecies());
        rings.setText(String.valueOf(mol.getProperties().getRings()));
        fillInImage(selectedItem, image);

    }

    public void stop(){
        disposable.dispose();
    }

    private void fillInImage(String selectedItem, JLabel image) throws IOException {
        URL url = new URL("https://www.ebi.ac.uk/chembl/api/data/image/" + selectedItem.trim() + "?format=png");
        BufferedImage bufImage = ImageIO.read(url);
        Icon icon = new ImageIcon(bufImage);
        image.setIcon(icon);

    }

}

