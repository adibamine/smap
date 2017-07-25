import React from 'react';
import TestUtils from 'react-addons-test-utils';
import {expect} from 'chai';
import {List, Map} from 'immutable';

import TermTable from '../../../main/React/components/terms/TermTable'

const {renderIntoDocument,
    scryRenderedDOMComponentsWithTag} = TestUtils;


describe('TermTable', () => {

    it('Renders list of elements', () => {
        // given
        const products = List.of(
            Map({id: 1, name: 'product1'}),
            Map({id: 2, name: 'product2'})
        );


        // when

        const component = renderIntoDocument(
            <TermTable terms={terms} />
        );

        // then
        const items = scryRenderedDOMComponentsWithTag(component, 'tr');
        expect(items.length).to.equal(2 + 1); // 1 for header
    })

})