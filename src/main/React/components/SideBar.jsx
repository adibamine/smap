import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { Link } from 'react-router'


export default class SideBar extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }

    render() {
        return  <div className="sidebar" data-color="purple">

                    <div className="sidebar-wrapper">
                        <div className="logo">

                            <Link to="/" className="simple-text">
                                OCTO
                            </Link>

                        </div>

                        <ul className="nav">
                            <li>
                                <Link to="/terms">
                                    <i className="pe-7s-ribbon"></i>
                                    Mots-clés
                                </Link>
                            </li>

                            <li>
                                <Link to="/terms/add">
                                    <i className="pe-7s-ribbon"></i>
                                    Monitoring
                                </Link>
                            </li>

                            <li>
                                <Link to="/comments/topInfluencers">
                                    <i className="pe-7s-ribbon"></i>
                                    Influenceurs
                                </Link>
                            </li>

                            <li>
                                <Link to="/insights/comments/attijari">
                                    <i className="pe-7s-ribbon"></i>
                                    Commentaires
                                </Link>
                            </li>

                            <li>
                                <Link to="/publications/create">
                                    <i className="pe-7s-ribbon"></i>
                                    Publications
                                </Link>
                            </li>

                            <li>
                                <Link to="insights/charts/page_impressions">
                                    <i className="pe-7s-ribbon"></i>
                                    Insights
                                </Link>
                            </li>

                            <li>
                                <Link to="/conversations">
                                    <i className="pe-7s-ribbon"></i>
                                    Inbox
                                </Link>
                            </li>

                        </ul>
                    </div>
                </div>

    }
}
