/**
 * @license Copyright (c) 2003-2017, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.md or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	 config.language = 'zh-cn';
	// config.uiColor = '#AADC6E';
	 config.toolbarCanCollapse = true;
	 config.toolbarGroups = [
	                 		{ name: 'document', groups: [ 'mode', 'document', 'doctools' ] },
	                 		{ name: 'clipboard', groups: [ 'clipboard', 'undo' ] },
	                 		{ name: 'forms', groups: [ 'forms' ] },
	                 		{ name: 'editing', groups: [ 'find', 'selection', 'spellchecker', 'editing' ] },
	                 		{ name: 'basicstyles', groups: [ 'basicstyles', 'cleanup' ] },
	                 		{ name: 'paragraph', groups: [ 'list', 'indent', 'blocks', 'align', 'bidi', 'paragraph' ] },
	                 		{ name: 'links', groups: [ 'links' ] },
	                 		{ name: 'insert', groups: [ 'insert' ] },
	                 		{ name: 'styles', groups: [ 'styles' ] },
	                 		{ name: 'colors', groups: [ 'colors' ] },
	                 		{ name: 'tools', groups: [ 'tools' ] },
	                 		{ name: 'others', groups: [ 'others' ] },
	                 		{ name: 'about', groups: [ 'about' ] }
	                 	];

	 config.removeButtons = 'Source,Save,NewPage,Preview,Print,Templates,Cut,Copy,Paste,PasteText,PasteFromWord,Redo,Undo,Find,Replace,SelectAll,Scayt,Button,Select,ImageButton,HiddenField,Superscript,Subscript,CopyFormatting,RemoveFormat,Blockquote,CreateDiv,BidiRtl,BidiLtr,Language,Anchor,Image,Flash,HorizontalRule,PageBreak,Iframe,Maximize,ShowBlocks,About';

	
};
