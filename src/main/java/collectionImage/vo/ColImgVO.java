package collectionImage.vo;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import core.pojo.Core;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@Table(name = "collectionImage")
@NoArgsConstructor
@AllArgsConstructor
public class ColImgVO extends Core {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer imageID;
	private Integer collectionID;
	@Transient
	private String collectionimgStr;
	private byte[] imageName;
	@Column(insertable = false)
	private Timestamp lastUpdateTime;
}
