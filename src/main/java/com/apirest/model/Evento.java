package com.apirest.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vo.EventoVo;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "Evento")
public class Evento {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 255)
    private String nome;  
	
	@Column(name = "sigla", nullable = false, length = 15)
    private String sigla;  
	
	@Column(name = "areaCon")
    private String areaCon; 
	
    @Column(name = "instOrg")
    private String instOrg; 
    
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_edicoes")
    private List<Edicao> edicoes;	 
    
    public static Evento create(EventoVo eventoVo) {
    	return new ModelMapper().map(eventoVo, Evento.class);
    }
    

}

