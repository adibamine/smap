import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

import {connect} from 'react-redux'
import * as actionCreators from 'actions/comments'

import InfluencersTable from 'components/comments/InfluencersTable'

class InfluencersListCmp extends React.Component {
    constructor(props) {
        super(props);
        console.log(this.props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    componentDidMount(){
        this.props.loadInfluencers()
    }

    getInfluencers(){
        if(!this.props.influencers){
            console.log("nada")
            return []
        }
        console.log(this.props.influencers)
        return this.props.influencers
    }

    render() {
        return <div className="container-fluid">
            <div className="row">
                <div className="col-md-12">
                    <div className="panel panel-default">
                        <div className="panel-body">
                            <h2>Top influenceurs</h2>
                            <InfluencersTable influencers = {this.getInfluencers()}/>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    }
};


function mapStateToProps(state) {
    return {
        influencers: state.comment.get('influencers')
    }
}

export const InfluencersList = connect(mapStateToProps, actionCreators)(InfluencersListCmp)