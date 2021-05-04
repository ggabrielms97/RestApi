package vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.apirest.model.Edicao;
import com.apirest.model.Evento;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@JsonPropertyOrder({"id","numero","ano","dataInicio","dataFim","cidade","pais","evento"})
@Data
@Entity
@Table(name = "Evento")
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class EdicaoVo extends RepresentationModel<EdicaoVo>	implements Serializable {
	
	private static final long serialVersionUID = -1373681541706744014L;

	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("numero")
	private Long numero;
	
	@JsonProperty("ano")
	private Integer ano;  
	
	@JsonProperty("dataInicio")
	private String dataInicio;  
	
	@JsonProperty("dataFim")
	private String dataFim; 
	
	@JsonProperty("cidade")
	private String cidade;
	 
	@JsonProperty("pais")
	private String pais; 
	
	@JsonProperty("evento")
	@ManyToOne
	@JoinColumn(name = "id_evento", nullable=false)
	private Evento evento;	
	
	public static EdicaoVo create(Edicao edicao) {
	    return new ModelMapper().map(edicao, EdicaoVo.class);
	   }


}
