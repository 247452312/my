package indi.uhyils.pojo.DTO.response.info;

import indi.uhyils.pojo.DTO.BaseDTO;
import indi.uhyils.pojo.DTO.MenuDTO;
import indi.uhyils.util.BeanUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * @author uhyils <247452312@qq.com>
 * @date 文件创建日期 2020年05月30日 07时38分
 */
public class MenuTreeDTO implements BaseDTO {

    private MenuDTO menuDTO;

    private Long id;

    private Long fid;

    private Integer iFrame;

    private String icon;

    private String name;

    private String title;

    private Integer sort;

    private String target;

    private Boolean type;

    private String url;

    private String href;

    private List<MenuTreeDTO> child = new ArrayList<>();

    public static MenuTreeDTO build(MenuDTO menuDTO) {
        MenuTreeDTO menuTreeDTO = new MenuTreeDTO();
        menuTreeDTO.setMenuDTO(menuDTO);
        BeanUtil.copyProperties(menuDTO, menuTreeDTO);
        menuTreeDTO.title = menuDTO.getName();
        menuTreeDTO.href = menuDTO.getUrl();
        return menuTreeDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getFid() {
        return fid;
    }

    public void setFid(Long fid) {
        this.fid = fid;
    }

    public Integer getiFrame() {
        return iFrame;
    }

    public void setiFrame(Integer iFrame) {
        this.iFrame = iFrame;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MenuDTO getMenuDTO() {
        return menuDTO;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setMenuDTO(MenuDTO menuDTO) {
        this.menuDTO = menuDTO;
    }

    public List<MenuTreeDTO> getChild() {
        return child;
    }

    public void setChild(List<MenuTreeDTO> child) {
        this.child = child;
    }
}
