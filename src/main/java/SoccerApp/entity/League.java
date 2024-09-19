package SoccerApp.entity;

import SoccerApp.utility.enums.EBolge;
import SoccerApp.utility.enums.EKume;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class League {
	private String ad;
	private List<String> takimlarIDList = new ArrayList<>();
	private String sezon;
	private EBolge bolge;
	private EKume kume;
	public Integer MAKS_LIG_TAKIM_SAYISI;
	private LocalDate baslangicTarihi;
	private LocalDate bitisTarihi;
	private Map<Integer,List<String>> fikstur;
	private TreeMap<Integer, String> puanTablosu;
}