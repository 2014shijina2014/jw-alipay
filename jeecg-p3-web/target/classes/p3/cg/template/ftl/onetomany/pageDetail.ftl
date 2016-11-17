<!DOCTYPE html>
<html lang="en">
#parse("content/base/back/common/head.vm")
<body style='overflow:scroll;overflow-x:hidden'>
	<div class="container bs-docs-container" style="width:100%;">
		<div class="row">
			<div class="panel panel-default">
				<div class="panel-heading">详情</div>
				<div class="panel-body">
					<form class="form-horizontal" role="form" id="dailogForm" action="$!{basePath}/${projectName}/${lowerName}.do?doEdit" method="POST">
					<fieldset disabled>
						<input type="hidden" id="btn_sub" class="btn_sub" />
						<input type="hidden" value="$!{${lowerName}.id}" name="id" id="id" />
						<#list columnDatas as item>
						<#if item.domainPropertyName != 'id'>
						<#if item.columnName?lower_case != 'del_stat' 
							    && item.columnName?lower_case != 'create_by'
								&& item.columnName?lower_case != 'create_name' 
								&& item.columnName?lower_case != 'create_date' 
								&& item.columnName?lower_case != 'update_by'
								&& item.columnName?lower_case != 'update_name' 
								&& item.columnName?lower_case != 'update_date' 
								&& item.columnName?lower_case != 'creator'
								&& item.columnName?lower_case != 'editor' 
								&& item.columnName?lower_case != 'create_dt' 
								&& item.columnName?lower_case != 'edit_dt' 
								&& item.columnName?lower_case != 'last_edit_dt' 
								&& item.columnName?lower_case != 'record_version'>
							<#if item.columnType == "datetime" ||item.columnType == "date" || item.columnType == "timestamp">
								<div class="form-group mno">
								    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:left;">${item.columnComment}</label>
								    <div class="col-sm-8">
								    	<input type="text" value="$!dateTool.format("yyyy-MM-dd",$!{${lowerName}.${item.domainPropertyName}})" name="${item.domainPropertyName}" id="${item.domainPropertyName}" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"   style="background: url('$!{basePath}/plug-in-ui/images/datetime.png') no-repeat scroll right center transparent;" <#if item.nullable != 'Y'> datatype="*" </#if> />
								    </div>
								</div>
							<#else>
								<div class="form-group mno">
								    <label for="inputEmail3" class="col-sm-2 control-label" style="text-align:left;">${item.columnComment}</label>
								    <div class="col-sm-8">
								    	<input type="text" value="$!{${lowerName}.${item.domainPropertyName}}" name="${item.domainPropertyName}" id="${item.domainPropertyName}" class="form-control" <#if item.nullable != 'Y'> datatype="*" </#if>/>
								    </div>
								</div>
							</#if>
						</#if>
						</#if>
						</#list> 
						
						<div class="form-group mno">
							<ul id="tab_menu" class="nav nav-tabs">
							<#list subEntityList as sub>
								<li <#if sub_index == 0> class="active"</#if>>
									<a href="#${sub.paramData.lowerName}" data-toggle="tab">
										${sub.paramData.codeName}
									</a>
                                </li>
							</#list>
                            </ul>
							<div id="tab_menu_content" class="tab-content">
								<#list subEntityList as sub>
									<div class="tab-pane fade in <#if sub_index == 0>active</#if>" id="${sub.paramData.lowerName}">
										<button type="button" id="${sub.paramData.lowerName}_add" class="btn btn-primary">添加</button>
										<button type="button" id="${sub.paramData.lowerName}_del" class="btn btn-danger">删除</button>
                                        <table class="table table-striped" id="${sub.paramData.lowerName}_table" style="margin-top:15px;">
											<thead>
													<th>ID</th>
												<#list sub.paramData.columnDatas as item>
													<#if item.domainPropertyName != 'id'>
													<#if item.columnName?lower_case != 'del_stat' 
													    && item.columnName?lower_case != 'create_by'
														&& item.columnName?lower_case != 'create_name' 
														&& item.columnName?lower_case != 'create_date' 
														&& item.columnName?lower_case != 'update_by'
														&& item.columnName?lower_case != 'update_name' 
														&& item.columnName?lower_case != 'update_date' 
														&& item.columnName?lower_case != 'creator'
														&& item.columnName?lower_case != 'editor' 
														&& item.columnName?lower_case != 'create_dt' 
														&& item.columnName?lower_case != 'edit_dt' 
														&& item.columnName?lower_case != 'last_edit_dt' 
														&& item.columnName?lower_case != 'record_version'>
														<th>${item.columnComment}</th>
														</#if>
													</#if>
												</#list>
                                            </thead>
											<tbody>
												#if($!{${sub.paramData.lowerName}Entities})
															#foreach($!{${sub.paramData.lowerName}} in $!{${sub.paramData.lowerName}Entities})
															#set($count = $!{velocityCount} - 1)
														    <tr>
																		    <td>
																				<input type="hidden" value="$!{${sub.paramData.lowerName}.id}" name="${sub.paramData.lowerName}Entities[$count].id" id="${sub.paramData.lowerName}Entities[$count].id"/>
																				<input type="checkbox"  name="${sub.paramData.lowerName}Entities[$count].idCheck" id="${sub.paramData.lowerName}Entities$count_idCheck" class="form-control" />
				                                                            </td>
														    <#list sub.paramData.columnDatas as item>
																<#if item.domainPropertyName != 'id'>
																	<#if item.columnName?lower_case != 'del_stat' 
																	    && item.columnName?lower_case != 'create_by'
																		&& item.columnName?lower_case != 'create_name' 
																		&& item.columnName?lower_case != 'create_date' 
																		&& item.columnName?lower_case != 'update_by'
																		&& item.columnName?lower_case != 'update_name' 
																		&& item.columnName?lower_case != 'update_date' 
																		&& item.columnName?lower_case != 'creator'
																		&& item.columnName?lower_case != 'editor' 
																		&& item.columnName?lower_case != 'create_dt' 
																		&& item.columnName?lower_case != 'edit_dt' 
																		&& item.columnName?lower_case != 'last_edit_dt' 
																		&& item.columnName?lower_case != 'record_version'>
																		<#if item.columnType == "datetime" ||item.columnType == "date" || item.columnType == "timestamp">
																			<td>
																				<input type="text" value="$!dateTool.format("yyyy-MM-dd",$!{${sub.paramData.lowerName}.${item.domainPropertyName}})" name="${sub.paramData.lowerName}Entities[$count].${item.domainPropertyName}" maxlength="16" id="${sub.paramData.lowerName}Entities[$count].${item.domainPropertyName}" class="form-control" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd'})"   style="background: url('$!{basePath}/plug-in-ui/images/datetime.png') no-repeat scroll right center transparent;" <#if item.nullable != 'Y'> datatype="*" </#if>/>
																			 </td>
																		<#else>
																			<td>
																				<input type="text" value="$!{${sub.paramData.lowerName}.${item.domainPropertyName}}" name="${sub.paramData.lowerName}Entities[$count].${item.domainPropertyName}" maxlength="16" id="${sub.paramData.lowerName}Entities[$count].${item.domainPropertyName}" class="form-control" <#if item.nullable != 'Y'> datatype="*" </#if>/>
																			 </td>
																		</#if>
																	</#if>
																	</#if>
															</#list>
															
															
                                                        	</tr>
															#end
														#end
                                            </tbody>
										</table>
									</div>
								</#list>
								
							</div>
							
						</div>
						</fieldset>
						<div class="form-group mno">
							<div class="col-sm-offset-1 col-sm-6">
								<button type="button" class="btn btn-default" id="formReturn" data-dismiss="modal" onclick="doUrl('$!{basePath}/${projectName}/${lowerName}.do?list')">返回</button>
							</div>
						</div>
						
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
