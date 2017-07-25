import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/terms'

import TermTable from 'components/terms/TermTable'

class TermsListCmp extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        this.props.loadAllTerms()
    }

    getTerms(){
        if(!this.props.terms){
            return []
        }

        return this.props.terms
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <TermTable terms = {this.getTerms()}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        needRefresh: state.term.get('needRefresh'),
        terms: state.term.get('terms')
    }
}

export const TermsList = connect(mapStateToProps, actionCreators)(TermsListCmp)