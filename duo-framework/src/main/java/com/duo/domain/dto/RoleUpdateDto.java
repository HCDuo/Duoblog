package com.duo.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 *
 * @author <a href="https://github.com/HCDUO">HCDUO</a>
 * @date:2023/8/3 23:23
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleUpdateDto {

    private Long id;

    private String roleName;

    private String roleKey;

    private Integer roleSort;

    private String status;

    private String remark;

    List<Integer> menuIds;

}
