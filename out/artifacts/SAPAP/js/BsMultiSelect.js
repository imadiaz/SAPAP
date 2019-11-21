import $ from 'jquery'

import MultiSelect from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/MultiSelect'
import LabelAdapter from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/LabelAdapter';
import {OptionsAdapterJson,OptionsAdapterElement} from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/OptionsAdapters';

import Bs4StylingMethodCss from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/Bs4StylingMethodCss'
import Bs4StylingMethodJs from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/Bs4StylingMethodJs'
import Bs4Styling from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/Bs4Styling';
import AddToJQueryPrototype from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/AddToJQueryPrototype'

import { Bs4SelectedItemContent, Bs4SelectedItemContentStylingMethodJs, Bs4SelectedItemContentStylingMethodCss} from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/Bs4SelectedItemContent';
import { Bs4DropDownItemContent, Bs4DropDownItemContentStylingMethodJs, Bs4DropDownItemContentStylingMethodCss} from '../../../../../Desktop/Bootstrap-4-Multi-Select-BsMultiSelect/js/src/Bs4DropDownItemContent';

(
    (window, $) => {
        AddToJQueryPrototype('BsMultiSelect',
            (element, settings, onDispose) => {
                let configuration = $.extend({}, settings); // settings used per jQuery intialization, configuration per element
                if (configuration.preBuildConfiguration)
                    configuration.preBuildConfiguration(element, configuration);
                
                var $element= $(element);
                let optionsAdapter = null;
                if (configuration.optionsAdapter)
                    optionsAdapter = configuration.optionsAdapter;
                else
                {
                    var trigger = function(eventName){
                        $element.trigger(eventName);
                    }
                    if (configuration.options){
                        optionsAdapter = OptionsAdapterJson(
                            element,
                            configuration.options,
                            configuration.getDisabled,
                            configuration.getIsValid,
                            configuration.getIsInvalid,
                            trigger );
                        if (!configuration.createInputId)
                            configuration.createInputId=()=>`${configuration.containerClass}-generated-filter-${element.id}`;
            
                    }else {
                        if (!configuration.label)
                        {
                            let $formGroup = $(element).closest('.form-group');
                            if ($formGroup.length == 1) {
                                let $label = $formGroup.find(`label[for="${element.id}"]`);
                                if ($label.length>0)
                                {   
                                    let label = $label.get(0);
                                    let forId = label.getAttribute('for');
                                    if (forId == element.id) {
                                        configuration.label = label;
                                    }
                                }   
                            }
                        }
                        optionsAdapter = OptionsAdapterElement(element, trigger);
                        if (!configuration.createInputId)
                            configuration.createInputId=()=>`${configuration.containerClass}-generated-input-${((element.id)?element.id:element.name).toLowerCase()}-id`;
                    }
                }

                let labelAdapter = LabelAdapter(configuration.label, configuration.createInputId);

                let useCss = configuration.useCss;
                let styling = configuration.styling;
                if (!configuration.adapter)
                {
                    let stylingMethod = configuration.stylingMethod;
                    if (!stylingMethod)
                    {
                        if (useCss)
                            stylingMethod = Bs4StylingMethodCss(configuration);
                        else
                            stylingMethod = Bs4StylingMethodJs(configuration);
                    }
                    styling = Bs4Styling(stylingMethod, configuration, $);
                }

                let selectedItemContent = configuration.selectedItemContent;
                if (!selectedItemContent){
                    let selectedItemContentStylingMethod = configuration.selectedItemContentStylingMethod;
                    if (!selectedItemContentStylingMethod)
                    {
                        if (useCss)
                            selectedItemContentStylingMethod=Bs4SelectedItemContentStylingMethodCss(configuration, $);
                        else
                            selectedItemContentStylingMethod=Bs4SelectedItemContentStylingMethodJs(configuration, $);
                    }
                    selectedItemContent = Bs4SelectedItemContent(selectedItemContentStylingMethod, configuration, $);
                }

                let dropDownItemContent = configuration.bs4DropDownItemContent;
                if (!dropDownItemContent){
                    let dropDownItemContentStylingMethod = configuration.dropDownItemContentStylingMethod;
                    if (useCss)
                        dropDownItemContentStylingMethod=Bs4DropDownItemContentStylingMethodCss(configuration, $);
                    else
                        dropDownItemContentStylingMethod=Bs4DropDownItemContentStylingMethodJs(configuration, $);
                    dropDownItemContent = Bs4DropDownItemContent(dropDownItemContentStylingMethod, configuration, $)
                }

                let createStylingComposite = function(container, selectedPanel, filterInputItem, filterInput, dropDownMenu){
                    return {
                        $container:$(container),
                        $selectedPanel:$(selectedPanel),
                        $filterInputItem:$(filterInputItem),
                        $filterInput:$(filterInput),
                        $dropDownMenu:$(dropDownMenu)
                    };
                }

                let multiSelect = new MultiSelect(
                    optionsAdapter,
                    styling,
                    selectedItemContent,
                    dropDownItemContent,
                    labelAdapter,
                    createStylingComposite,
                    configuration,
                    onDispose,
                    window);

                if (configuration.postBuildConfiguration)
                    configuration.postBuildConfiguration(element, multiSelect);
                
                multiSelect.init();
                return multiSelect;
            }, $);
    }
)(window, $)
