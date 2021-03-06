<%--
/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */
--%>

<%@ include file="/document_library/init.jsp" %>

<%
String redirect = ParamUtil.getString(request, "redirect");

String commonTagNames = (String)request.getAttribute(DLWebKeys.DOCUMENT_LIBRARY_COMMON_TAG_NAMES);

boolean portletTitleBasedNavigation = GetterUtil.getBoolean(portletConfig.getInitParameter("portlet-title-based-navigation"));

if (portletTitleBasedNavigation) {
	portletDisplay.setShowBackIcon(true);
	portletDisplay.setURLBack(redirect);

	renderResponse.setTitle(LanguageUtil.get(request, "edit-tags"));
}
%>

<liferay-portlet:actionURL name="/document_library/edit_tags" varImpl="editTagsURL" />

<div <%= portletTitleBasedNavigation ? "class=\"container-fluid-1280\"" : StringPool.BLANK %>>
	<aui:form action="<%= editTagsURL %>" cssClass="lfr-dynamic-form" enctype="multipart/form-data" method="post" name="fm">
		<aui:input name="<%= Constants.CMD %>" type="hidden" />
		<aui:input name="redirect" type="hidden" value="<%= redirect %>" />

		<c:if test="<%= !portletTitleBasedNavigation %>">
			<liferay-ui:header
				backURL="<%= redirect %>"
				title="edit-tags"
			/>
		</c:if>

		<div class="lfr-form-content">
			<liferay-asset:asset-tags-error />

			<aui:fieldset-group markupView="lexicon">
				<aui:fieldset>

					<%
					List<FileEntry> fileEntries = (List<FileEntry>)request.getAttribute(WebKeys.DOCUMENT_LIBRARY_FILE_ENTRIES);
					%>

					<aui:input name="rowIdsFileEntry" type="hidden" value="<%= ListUtil.toString(fileEntries, FileEntry.FILE_ENTRY_ID_ACCESSOR) %>" />
					<aui:input name="commonTagNames" type="hidden" value="<%= commonTagNames %>" />

					<c:choose>
						<c:when test="<%= fileEntries.size() == 1 %>">

							<%
							FileEntry fileEntry = fileEntries.get(0);
							%>

							<liferay-ui:message arguments="<%= fileEntry.getTitle() %>" key="you-are-editing-the-tags-for-x" />
						</c:when>
						<c:otherwise>
							<liferay-ui:message arguments="<%= fileEntries.size() %>" key="you-are-editing-the-common-tags-for-x-items" /> <liferay-ui:message key="select-append-or-replace-current-tags" />

							<div class="form-group" id="<portlet:namespace />tagOptions">
								<aui:input checked="<%= true %>" label="append" name="append" type="radio" value="<%= true %>" />

								<aui:input checked="<%= false %>" label="replace" name="append" type="radio" value="<%= false %>" />
							</div>
						</c:otherwise>
					</c:choose>

					<liferay-asset:asset-tags-selector
						tagNames="<%= commonTagNames %>"
					/>
				</aui:fieldset>
			</aui:fieldset-group>
		</div>

		<aui:button-row>
			<aui:button type="submit" value="save" />
			<aui:button href="<%= redirect %>" type="cancel" />
		</aui:button-row>
	</aui:form>
</div>