package system_mangement;
import core_classes.*;
public class populateSystem {
    public static crimeSystem populateSystemData() {
        crimeSystem sys = new crimeSystem();

        department homicideDept = new department("Miami Metro Homicide", "MMPDH01", "19800515");
        sys.allDepartments.add(homicideDept);

        systemAdmin adminMatthews = new systemAdmin("Tom Matthews", "ADM001", 62, "admin123");
        sys.allAdmins.add(adminMatthews);

        policeOfficer debra = new policeOfficer(
                65000,
                "5550101",
                homicideDept,
                32,
                "Debra Morgan",
                "OFF892",
                "debpass123"
        );

        policeOfficer angel = new policeOfficer(
                75000,
                "5550102",
                homicideDept,
                45,
                "Angel Batista",
                "OFF442",
                "fedora"
        );

        policeOfficer doakes = new policeOfficer(
                72000,
                "5550103",
                homicideDept,
                39,
                "James Doakes",
                "OFF202",
                "surprise"
        );

        policeOfficer laguerta = new policeOfficer(
                85000,
                "5550104",
                homicideDept,
                42,
                "Maria LaGuerta",
                "OFF101",
                "politics"
        );

        sys.allOfficers.add(debra);
        sys.allOfficers.add(angel);
        sys.allOfficers.add(doakes);
        sys.allOfficers.add(laguerta);

        homicideDept.addOfficer(debra);
        homicideDept.addOfficer(angel);
        homicideDept.addOfficer(doakes);
        homicideDept.addOfficer(laguerta);

        criminal brianMoser = new criminal(
                35,
                "Brian Moser",
                "CRIMITK",
                "Unknown",
                "Extreme"
        );

        criminal arthurMitchell = new criminal(
                60,
                "Arthur Mitchell",
                "CRIMTRN",
                "Four Walls Build",
                "High"
        );

        criminal travisMarshall = new criminal(
                32,
                "Travis Marshall",
                "CRIMDDK",
                "Abandoned Church",
                "High"
        );

        sys.allCriminals.add(brianMoser);
        sys.allCriminals.add(arthurMitchell);
        sys.allCriminals.add(travisMarshall);

        Case iceTruckCase = new Case(
                "CASE200601",
                "Serial Homicide",
                "Victims found drained of blood and bodies severly dismembered.",
                "The Ice Truck Killer",
                "20061001",
                homicideDept
        );
        iceTruckCase.addUpdate("20061005", "Fingerprint found on doll head.");
        iceTruckCase.addUpdate("20061012", "Suspect identified as Rudy Cooper.");

        Case trinityCase = new Case(
                "CASE200904",
                "Ritualistic Murder",
                "Cycles of three deaths involving a bathtub, a jumper, and bludgeoning.",
                "The Trinity Killer",
                "20090927",
                homicideDept
        );

        Case bhbCase = new Case(
                "CASE200702",
                "Vigilante Homicide",
                "Dismembered criminals found in underwater graveyard.",
                "The Bay Harbor Butcher",
                "20070215",
                homicideDept
        );

        sys.allCases.add(iceTruckCase);
        sys.allCases.add(trinityCase);
        sys.allCases.add(bhbCase);

        iceTruckCase.assignOfficers(debra);
        iceTruckCase.assignOfficers(angel);

        trinityCase.assignOfficers(debra);
        trinityCase.assignOfficers(angel);

        bhbCase.assignOfficers(doakes);
        bhbCase.assignOfficers(laguerta);

        iceTruckCase.assignCriminal(brianMoser);
        trinityCase.assignCriminal(arthurMitchell);

        return sys;
    }
}
