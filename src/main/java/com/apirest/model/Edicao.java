package com.apirest.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import vo.EdicaoVo;

@Data
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "Edicao")
public class Edicao {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "nome", nullable = false, length = 255)
    private Long numero;
	
	@Column(name = "ano", nullable = false, length = 255)
    private Integer ano;  
	
	@Column(name = "dataInicio", nullable = false, length = 255)
    private String dataInicio; 
	
	@Column(name = "dataFim", nullable = false, length = 255)
    private String dataFim;  
	
	@Column(name = "cidade", nullable = false, length = 255)
    private String cidade;
	
	@Column(name = "pais", nullable = false, length = 255)
    private String pais; 

	@ManyToOne
	@JoinColumn(name = "id_evento", nullable=false)
    private Evento evento;
    
    public static Edicao create(EdicaoVo edicaoVo) {
    	return new ModelMapper().map(edicaoVo, Edicao.class);
    }
    
}
